package com.example.hp.chhabras;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hp.chhabras.Adapter.GalleryAdapter_temp;
import com.example.hp.chhabras.Model.Gallery_structure_temp;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 27-06-2018.
 */

public class Gallery extends AppCompatActivity implements CustomItemClickListener {

    GridView gallery_gridView;

    private List<Gallery_structure_temp> moreList = new ArrayList<>();
    private RecyclerView recyclerView;
    GalleryAdapter_temp adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ProgressDialog pd = new ProgressDialog(Gallery.this);
        pd.setMessage("Please Wait..");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();
        /*
        setContentView(R.layout.activity_gallery);

        gallery_gridView = (GridView) findViewById(R.id.gallery_gridView);
        gallery_gridView.setAdapter(new GalleryAdapter(this));

        gallery_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Gallery.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        
        */

        setContentView(R.layout.activity_gallery_temp);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new GalleryAdapter_temp(Gallery.this, moreList);

        moreList.add(new Gallery_structure_temp(R.drawable.blurred, "One"));
        moreList.add(new Gallery_structure_temp(R.drawable.blurred, "Two"));
        moreList.add(new Gallery_structure_temp(R.drawable.blurred, "Three"));
        moreList.add(new Gallery_structure_temp(R.drawable.blurred, "Four"));
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        pd.dismiss();
    }

    @Override
    public void onClick(String imageUrl) {

        Intent intent = new Intent(Gallery.this,FullImage.class);
        intent.putExtra("IMAGE", Integer.parseInt(imageUrl));
        startActivity(intent);
    }
}