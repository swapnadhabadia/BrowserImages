package com.example.browseimages.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.browseimages.BaseApplication;
import com.example.browseimages.R;
import com.example.browseimages.utils.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;



public class LargeDisplayFragment extends Fragment{
    public static final String TAG ="ProductDetailFragment" ;
    private String webformatURL;
     View root;
@BindView(R.id.displayImg)
    ImageView displayImage;
    private ImageLoader imageLoader;

    public static Fragment newInstance(String webformatURL) {
        LargeDisplayFragment fragment = new LargeDisplayFragment();
        Bundle args = new Bundle();
        args.putString(Constants.AppConstants.IMAGE, webformatURL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = BaseApplication.getImageLoader();
        if (getArguments() != null) {
            webformatURL = getArguments().getString(Constants.AppConstants.IMAGE);
            Log.i(TAG, "onCreateView:  String : " + webformatURL);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null)
            container.removeAllViews();

        root = inflater.inflate(R.layout.display_item, container, false);
        ButterKnife.bind(this, root);

        imageLoader.displayImage(webformatURL,displayImage);
        return root;
    }
}
