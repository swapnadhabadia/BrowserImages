package com.example.browseimages.view;

import android.content.Context;

import com.example.browseimages.model.Hit;

import java.util.List;

public interface ListMainInterface {
    void setListImages(Context context, List<Hit> hits);
}
