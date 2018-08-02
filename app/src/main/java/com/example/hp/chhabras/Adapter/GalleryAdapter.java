package com.example.hp.chhabras.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.chhabras.R;

/**
 * Created by hp on 27-06-2018.
 */

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;

    public GalleryAdapter(Context c) {
        mContext = c;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        View grid;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            grid = new View(mContext);
            grid = layoutInflater.inflate(R.layout.gallery_structure, null);
        } else {
            grid = (View)convertView;
        }
        imageView = (ImageView)grid.findViewById(R.id.gallery_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mThumbIds[position]);

        TextView textView = (TextView)grid.findViewById(R.id.gallery_text);
        textView.setText(mThumbIdsText[position]);

        return grid;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.blurred, R.drawable.blurred,
            R.drawable.blurred, R.drawable.blurred
    };

    // references to our imageTexts
    private String[] mThumbIdsText = {
            "One", "Two", "Three", "Four"
    };
}