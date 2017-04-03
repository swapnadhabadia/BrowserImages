package com.example.browseimages.view;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.browseimages.R;
import com.example.browseimages.model.ImageListMainModel;
import com.example.browseimages.networking.APIRequest;
import com.example.browseimages.networking.Connectivity;
import com.example.browseimages.networking.VolleyUtil;
import com.example.browseimages.utils.DialogUtil;
import com.example.browseimages.utils.URLConstants;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;


public class ListInteractor {
    private ListPresenter presenterFragment;

    public ListInteractor(Context context, ListPresenter presenterFragment) {

        this.presenterFragment=presenterFragment;
    }

    public void getListResponse(final Context context, String searchString) {


        searchString.replaceAll(" ", "+").toLowerCase();

        try {
            if (Connectivity.isConnected(context)) {
                final APIRequest.Builder<ImageListMainModel> builder = new APIRequest.Builder<>(context, Request.Method.GET,
                        ImageListMainModel.class, URLConstants.BASE_URL+searchString+"&image_type=photo",
                        new Response.Listener<ImageListMainModel>() {

                            @Override
                            public void onResponse(ImageListMainModel response) {

                                if(response !=null)
                                {
                                    if(response.hits.size()>0)
                                    {
                                        presenterFragment.setListImages(context,response.hits);
                                    }
                                    else
                                    {
                                        presenterFragment.setDisplayMessage();
                                    }
                                }
                                else
                                {
                                    presenterFragment.setDisplayMessage();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //presenterFragment.hideProgressDialog();
                        if (error.networkResponse == null) {
                            Log.i(TAG, "run: fail  " + error.getMessage());
                        }

                    }
                });
                APIRequest request = builder.build();
                VolleyUtil.getInstance(context).addToRequestQueue(request);
            }
            else {
                DialogUtil.showNoNetworkAlert(context);
            }
        }
        catch(Exception e)
        {
            Log.e(TAG, e.toString());
        }
    }
}
