package com.example.hp.chhabras.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chhabras.Model.Items_structure;
import com.example.hp.chhabras.R;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hp on 28-06-2018.
 */

public class Items_adapter extends RecyclerView.Adapter<Items_adapter.MyViewHolder> {

    private List<Items_structure> moreList;
    private Context context;
    public int mSelectedItem = -1;
    SharedPreferences sharedPreferences, sp;
    SharedPreferences.Editor editor, e;
    String s;
    customButtonListener listener;

    public interface customButtonListener {
        void onButtonClickListener(String itemName);
    }

    public void setCustomButtonListener(customButtonListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name, price_per_kg, price, add, inc, dec, num;
        public LinearLayout incDecLayout;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            item_name = (TextView) view.findViewById(R.id.item_name);
            price = (TextView) view.findViewById(R.id.item_price);
            price_per_kg = (TextView) view.findViewById(R.id.item_per_kg_price);
            add = (TextView) view.findViewById(R.id.add);
            incDecLayout = (LinearLayout) view.findViewById(R.id.incDecLayout);
            inc = (TextView) view.findViewById(R.id.inc);
            dec = (TextView) view.findViewById(R.id.dec);
            num = (TextView) view.findViewById(R.id.num);
            sharedPreferences = context.getSharedPreferences("Items", 0);
            editor = sharedPreferences.edit();

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    s = moreList.get(mSelectedItem).getItemName();

                    add.setVisibility(View.GONE);
                    incDecLayout.setVisibility(View.VISIBLE);
                    int count = Integer.parseInt(num.getText().toString());
                    num.setText(""+(count+1));

                    sp = context.getSharedPreferences(s,0);
                    e = sp.edit();
                    e.putString("price", moreList.get(mSelectedItem).getPrice());
                    e.putString("pricePerKg", moreList.get(mSelectedItem).getPricePerKg());
                    e.putInt("qty", count+1);
                    e.apply();

                    Set<String> itemNames = sharedPreferences.getStringSet("itemNames",  new HashSet<String>());
                    itemNames.add(s);
                    //Log.e("ItemsInCart", itemNames.toString());
                    int c = sharedPreferences.getInt("count", 0);
                    editor.putInt("count", c+1);
                    editor.putStringSet("itemNames", itemNames);
                    editor.putInt("price", sharedPreferences.getInt("price", 0)+Integer.parseInt(moreList.get(mSelectedItem).getPrice()));
                    editor.apply();

                    listener.onButtonClickListener(s);
                }
            };
            add.setOnClickListener(clickListener);

            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(num.getText().toString());
                    num.setText(""+(count+1));

                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    s = moreList.get(mSelectedItem).getItemName();
                    sp = context.getSharedPreferences(s,0);
                    e = sp.edit();
                    e.putInt("qty", count+1);
                    e.apply();

                    int c = sharedPreferences.getInt("count", 0);
                    editor.putInt("count", c+1);
                    editor.putInt("price", sharedPreferences.getInt("price", 0)+Integer.parseInt(moreList.get(mSelectedItem).getPrice()));

                    editor.apply();

                    listener.onButtonClickListener(s);
                }
            });

            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    s = moreList.get(mSelectedItem).getItemName();

                    if (num.getText().toString().equals("1")) {
                        incDecLayout.setVisibility(View.GONE);
                        add.setVisibility(View.VISIBLE);

                        Set<String> set = sharedPreferences.getStringSet("itemNames",  new HashSet<String>());
                        set.remove(s);
                        editor.putStringSet("itemNames", set);

                        //Listener for cart activity
                        listener.onButtonClickListener("Remove");
                    }

                    int count = Integer.parseInt(num.getText().toString());
                    num.setText(""+(count-1));

                    sp = context.getSharedPreferences(s,0);
                    e = sp.edit();
                    e.putInt("qty", count-1);
                    e.apply();

                    int c = sharedPreferences.getInt("count", 0);
                    editor.putInt("count", c-1);
                    editor.putInt("price", sharedPreferences.getInt("price", 0)-Integer.parseInt(moreList.get(mSelectedItem).getPrice()));
                    editor.apply();

                    listener.onButtonClickListener(s);
                }
            });
        }
    }

    public Items_adapter(Context mContext, List<Items_structure> moreList) {
        this.moreList = moreList;
        this.context = mContext;
    }

    @Override
    public Items_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_fragment_structure, parent, false);

        return new Items_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Items_adapter.MyViewHolder holder, int position) {
        Items_structure members = moreList.get(position);
        holder.item_name.setText(members.getItemName());
        holder.price_per_kg.setText(members.getPricePerKg());
        holder.price.setText(members.getPrice());

        sp = context.getSharedPreferences(members.getItemName(),0);
        int qty = sp.getInt("qty", 0);
        if (qty != 0) {
            holder.incDecLayout.setVisibility(View.VISIBLE);
            holder.num.setText(""+qty);
        }
    }

    @Override
    public int getItemCount() {
        return moreList.size();
    }
}
