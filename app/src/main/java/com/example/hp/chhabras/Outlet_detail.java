package com.example.hp.chhabras;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.hp.chhabras.Adapter.GalleryAdapter_temp;
import com.example.hp.chhabras.Model.Gallery_structure_temp;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 27-06-2018.
 */

public class Outlet_detail extends AppCompatActivity implements CustomItemClickListener {

    private List<Gallery_structure_temp> moreList = new ArrayList<>();
    private RecyclerView recyclerView;
    GalleryAdapter_temp adapter;
    Button call_outlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_outlet_detail);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        call_outlet = (Button) findViewById(R.id.call_outlet);

        call_outlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno="tel:"+"9413340520";
                Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse(phno));
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new GalleryAdapter_temp(Outlet_detail.this, moreList);

        moreList.add(new Gallery_structure_temp(R.drawable.blurred, " "));
        moreList.add(new Gallery_structure_temp(R.drawable.blurred, " "));
        moreList.add(new Gallery_structure_temp(R.drawable.blurred, " "));
        moreList.add(new Gallery_structure_temp(R.drawable.blurred, " "));
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(String imageUrl) {

        Intent intent = new Intent(Outlet_detail.this,FullImage.class);
        intent.putExtra("IMAGE", Integer.parseInt(imageUrl));
        startActivity(intent);
    }
}