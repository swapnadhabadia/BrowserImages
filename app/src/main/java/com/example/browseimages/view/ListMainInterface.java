package com.example.browseimages.view;

import android.content.Context;

import com.example.browseimages.model.Hit;

import java.util.List;

public interface ListMainInterface {
    void setListImages(Context context, List<Hit> hits);

    void setDisplayMessage();

    void showProgressDialog(String message, boolean indeterminate, boolean isCancelable);

    void cancelProgressDialog();
}
