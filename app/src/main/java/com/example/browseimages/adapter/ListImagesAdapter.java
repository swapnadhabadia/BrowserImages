package com.example.browseimages.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.browseimages.BaseApplication;
import com.example.browseimages.R;
import com.example.browseimages.model.Hit;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.ButterKnife;

public class ListImagesAdapter extends BaseAdapter {
    private  List<Hit> imageList;
    private  Context context;

    ImageLoader imageLoader;

    public ListImagesAdapter(Context context, List<Hit> hits) {
        this.context=context;
        this.imageList =hits;
        imageLoader = BaseApplication.getImageLoader();
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_items, null);
            ButterKnife.bind(this, convertView);
            holder = new ViewHolder();

            holder.listOfImage = (ImageView) convertView.findViewById(R.id.listOfImage);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Hit imgItem = imageList.get(position);
        imageLoader.displayImage(imgItem.previewURL,holder.listOfImage);

        return convertView;
    }

    private class ViewHolder {
        ImageView listOfImage;

    }
}
