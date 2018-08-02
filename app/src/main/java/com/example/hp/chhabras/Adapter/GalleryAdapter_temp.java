package com.example.hp.chhabras.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.chhabras.Model.Gallery_structure_temp;
import com.example.hp.chhabras.R;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.List;

/**
 * Created by hp on 27-06-2018.
 */

public class GalleryAdapter_temp extends RecyclerView.Adapter<GalleryAdapter_temp.MyViewHolder> {

    private List<Gallery_structure_temp> moreList;
    private Context context;
    public int mSelectedItem = -1;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            image = (ImageView) view.findViewById(R.id.gallery_image);
            text = (TextView) view.findViewById(R.id.gallery_text);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();

                    listener.onClick(String.valueOf(moreList.get(mSelectedItem).getImage()));
                }
            };
            itemView.setOnClickListener(clickListener);
        }
    }

    public void setClickListener(CustomItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    public GalleryAdapter_temp(Context mContext, List<Gallery_structure_temp> moreList) {
        this.moreList = moreList;
        this.context = mContext;
    }

    @Override
    public GalleryAdapter_temp.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_structure, parent, false);

        return new GalleryAdapter_temp.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter_temp.MyViewHolder holder, int position) {
        Gallery_structure_temp members = moreList.get(position);
        holder.text.setText(members.getText());
        holder.image.setImageResource(members.getImage());
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return moreList.size();
    }
}