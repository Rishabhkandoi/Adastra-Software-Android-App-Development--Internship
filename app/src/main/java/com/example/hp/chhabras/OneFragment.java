package com.example.hp.chhabras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.chhabras.Adapter.Items_adapter;
import com.example.hp.chhabras.Model.Items_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 28-06-2018.
 */

public class OneFragment extends Fragment implements Items_adapter.customButtonListener {

    private List<Items_structure> moreList = new ArrayList<>();
    private RecyclerView recyclerView;
    Items_adapter adapter;
    Context context;
    SharedPreferences sp;
    Items items;
    SessionManager session;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        session = new SessionManager(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.items_fragment, container, false);
        recyclerView = new RecyclerView(context);
        items = (Items) getActivity();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Items_adapter(context, moreList);
        adapter.setCustomButtonListener(OneFragment.this);

        moreList.add(new Items_structure("Name 1", "200/kg", "50"));
        moreList.add(new Items_structure("Name 2", "240/kg", "60"));

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        items.footerCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.isUserLoggedIn()) {
                    Intent i = new Intent(context, Cart.class);
                    i.putExtra("intentCart", "Items");
                    i.putExtra("message", items.m);
                    startActivity(i);
                    items.finish();
                }
                else {
                    Intent i = new Intent(context, Login.class);
                    i.putExtra("message", "Items");
                    startActivity(i);
                }
            }
        });

        SharedPreferences s = context.getSharedPreferences("Items", 0);
        int count = s.getInt("count", 0);
        int totalPrice = s.getInt("price", 0);

        if(count == 0) {
            items.bottomCart.setVisibility(View.INVISIBLE);
        }
        else {
            items.bottomCart.setVisibility(View.VISIBLE);
        }

        items.footerQty.setText(""+count+" ITEMS IN CART");
        items.footerPrice.setText("RS. "+totalPrice);

        return recyclerView;
    }

    @Override
    public void onButtonClickListener (String itemName) {

        SharedPreferences s = context.getSharedPreferences("Items", 0);
        int count = s.getInt("count", 0);
        int totalPrice = s.getInt("price", 0);

        if(count == 0) {
            items.bottomCart.setVisibility(View.INVISIBLE);
        }
        else {
            items.bottomCart.setVisibility(View.VISIBLE);
        }

        items.footerQty.setText(""+count+" ITEMS IN CART");
        items.footerPrice.setText("RS. "+totalPrice);
        //Toast.makeText(context, "count:"+count+" itemName:"+sp.getString("price", "default value"), Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        items.onRestart();
//        //When BACK BUTTON is pressed, the activity on the stack is restarted
//        //Do what you want on the refresh procedure here
//    }
}