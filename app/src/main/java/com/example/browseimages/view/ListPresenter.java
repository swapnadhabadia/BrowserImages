package com.example.browseimages.view;

import android.content.Context;

import com.example.browseimages.model.Hit;

import java.util.List;

public class ListPresenter {
    private final ListMainInterface listFragmentInterface;
    private final ListInteractor interactorListFragment;

    public ListPresenter(Context context, ListMainInterface listFragmentInterface) {
        this.listFragmentInterface = listFragmentInterface;
        interactorListFragment = new ListInteractor(context, this);
    }

    public void getImgListResponse(Context context, String searchString) {
        interactorListFragment.getListResponse(context,searchString);

    }

    public void setListImages(Context context, List<Hit> hits) {
        listFragmentInterface.setListImages(context,hits);
    }

    public void setDisplayMessage() {
        listFragmentInterface.setDisplayMessage();
    }
}
