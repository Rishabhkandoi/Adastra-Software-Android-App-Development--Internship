package com.example.hp.chhabras;

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

import com.example.hp.chhabras.Adapter.Dialog_select_outlet_adapter;
import com.example.hp.chhabras.Model.Dialog_select_outlet_structure;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 26-06-2018.
 */

public class Dialog_select_outlet extends AppCompatActivity implements CustomItemClickListener {

    private List<Dialog_select_outlet_structure> moreList = new ArrayList<>();
    private RecyclerView recyclerView;
    Dialog_select_outlet_adapter adapter;
    String areaAddress, mes, message;
    TextView ok, cancel;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dialog_select_outlet);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ok = (TextView) findViewById(R.id.dialog_ok);
        cancel = (TextView) findViewById(R.id.dialog_cancel);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Dialog_select_outlet_adapter(Dialog_select_outlet.this, moreList);

        moreList.add(new Dialog_select_outlet_structure("Malviya Nagar", "Sample Address 1"));
        moreList.add(new Dialog_select_outlet_structure("Nirman Nagar", "Sample Address 2"));
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        mes = bundle.getString("mes");
        message = bundle.getString("message");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Dialog_select_outlet.this, areaAddress, Toast.LENGTH_LONG).show();

                sharedPreferences = getSharedPreferences("SelectOutlet", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Address", areaAddress);
                editor.apply();

                if (mes.equals("Home")) {
                    Intent i = new Intent(Dialog_select_outlet.this, Items.class);
                    startActivity(i);
                    finish();
                }
                else {

                    Bundle bundle = getIntent().getExtras();
                    String m = bundle.getString("intentCart");

                    Intent i = new Intent(Dialog_select_outlet.this, Cart.class);
                    i.putExtra("outlet", areaAddress);
                    i.putExtra("intentCart", m);
                    i.putExtra("message", message);
                    startActivity(i);
                    finish();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mes.equals("Home")) {
                    Intent i = new Intent(Dialog_select_outlet.this, MainActivity.class);
                    startActivity(i);
                    //finish();
                }
                else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(String address) {
        areaAddress = address;
    }
}