package com.example.browseimages.view;

import
        android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.browseimages.adapter.ListImagesAdapter;

import com.example.browseimages.R;
import com.example.browseimages.model.Hit;
import com.example.browseimages.networking.Connectivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageListFragment extends Fragment implements ListMainInterface, AdapterView.OnItemClickListener {

    public static final String TAG = "FragmentImgList";
     View root;
    private String searchString;
    private ListPresenter presenterFragment;
@BindView(R.id.listView)
    ListView listViewOfImages;
    private List<Hit> imageListModel;

    public static Fragment newInstance(String s) {
        ImageListFragment fragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putString("PRODUCT_NAME", s);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchString = getArguments().getString("PRODUCT_NAME");
            Log.i(TAG, "onCreateView:  String : " + searchString);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null)
            container.removeAllViews();

        root = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, root);

        presenterFragment = new ListPresenter(getActivity(),this);
        checkInternetConnectivityAndProceed();
            return root;
    }

    private void checkInternetConnectivityAndProceed() {
        if(Connectivity.isConnected(getContext())) {
            presenterFragment.getImgListResponse(getActivity(), searchString);
        }

    }

    @Override
    public void setListImages(Context context, List<Hit> hits) {
        imageListModel=hits;
        ListImagesAdapter listImagesBaseAdapter = new ListImagesAdapter(getActivity(), hits);
        listViewOfImages.setAdapter(listImagesBaseAdapter);
        listViewOfImages.setOnItemClickListener(this);
    }

    @Override
    public void setDisplayMessage() {
        Toast.makeText(getContext(), R.string.no_result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, LargeDisplayFragment.newInstance(imageListModel.get(position).webformatURL), LargeDisplayFragment.TAG).addToBackStack(LargeDisplayFragment.TAG).commit();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
