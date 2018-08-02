package com.example.hp.chhabras;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chhabras.Adapter.Outlet_adapter;
import com.example.hp.chhabras.Model.Dialog_select_outlet_structure;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 27-06-2018.
 */

public class Outlet extends AppCompatActivity implements CustomItemClickListener {

    private List<Dialog_select_outlet_structure> moreList = new ArrayList<>();
    private RecyclerView recyclerView;
    Outlet_adapter adapter;
    SharedPreferences sharedPreferences;
    LinearLayout order_online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_outlet);

        final ProgressDialog pd = new ProgressDialog(Outlet.this);
        pd.setMessage("Please Wait..");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Outlet_adapter(Outlet.this, moreList);

        moreList.add(new Dialog_select_outlet_structure("Malviya Nagar", "Sample Address 1"));
        moreList.add(new Dialog_select_outlet_structure("Nirman Nagar", "Sample Address 2"));

        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        pd.dismiss();

        order_online = (LinearLayout) findViewById(R.id.home_order_online);
        order_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Outlet.this, Items.class);
                i.putExtra("message", "Outlet");
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(String address) {

        sharedPreferences = getSharedPreferences("Outlet", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AreaAddress", address);
        editor.apply();

        Intent i = new Intent(Outlet.this, Outlet_detail.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Outlet.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}