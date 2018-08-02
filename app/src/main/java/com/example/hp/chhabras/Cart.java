package com.example.hp.chhabras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chhabras.Adapter.Items_adapter;
import com.example.hp.chhabras.Model.Items_structure;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by hp on 04-07-2018.
 */

public class Cart extends AppCompatActivity implements Items_adapter.customButtonListener {

    EditText name, number, specialInstr;
    LinearLayout address, payment;
    private RecyclerView recyclerView;
    private List<Items_structure> moreList = new ArrayList<>();
    Items_adapter adapter;
    SharedPreferences sp;
    SessionManager session;
    Context context;
    RadioGroup deliveryOption;
    RadioButton options, homeDelivery, takeAway;
    TextView deliveryAddress, selectAddress;
    String message, m, n;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cart);

        bundle = getIntent().getExtras();
        m = bundle.getString("intentCart");
        n = bundle.getString("message");

        context = getApplicationContext();
        name = (EditText) findViewById(R.id.cart_name);
        number = (EditText) findViewById(R.id.cart_phoneNumber);
        specialInstr = (EditText) findViewById(R.id.cart_specialInstructions);
        address = (LinearLayout) findViewById(R.id.cart_selectAddress);
        payment = (LinearLayout) findViewById(R.id.cart_selectPayment);
        deliveryOption = (RadioGroup) findViewById(R.id.cart_deliveryOption);
        homeDelivery = (RadioButton) findViewById(R.id.cart_deliveryOption_homeDelivery);
        takeAway = (RadioButton) findViewById(R.id.cart_deliveryOption_takeAway);
        deliveryAddress = (TextView) findViewById(R.id.cart_textView_deliveryAddress);
        selectAddress = (TextView) findViewById(R.id.cart_textView_selectAddress);

        try {
            message = bundle.getString("outlet");
            if(message.length()>0) {
                takeAway.setChecked(true);
                deliveryAddress.setText("Select Outlet");
                selectAddress.setText(message);
            }
        }
        catch (Exception e){}

        homeDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = deliveryOption.getCheckedRadioButtonId();
                options = (RadioButton) findViewById(selectedId);
                //Toast.makeText(Cart.this, options.getText().toString(), Toast.LENGTH_LONG).show();

                deliveryAddress.setText("Delivery Address");
                selectAddress.setText("Please Select Delivery Address");

                Toast.makeText(Cart.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
            }
        });

        takeAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = deliveryOption.getCheckedRadioButtonId();
                options = (RadioButton) findViewById(selectedId);
                //Toast.makeText(Cart.this, options.getText().toString(), Toast.LENGTH_LONG).show();

                deliveryAddress.setText("Select Outlet");
                selectAddress.setText("Please Select Outlet");

                Bundle bundle = getIntent().getExtras();
                String m = bundle.getString("intentCart");

                Intent i = new Intent(Cart.this, Dialog_select_outlet.class);
                i.putExtra("mes", "Cart");
                i.putExtra("message", n);
                i.putExtra("intentCart", m);
                startActivity(i);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectAddress.getText().toString().equals("Please Select Delivery Address")) {
                    Toast.makeText(Cart.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
                }
                else {

                    Bundle bundle = getIntent().getExtras();
                    String m = bundle.getString("intentCart");

                    Intent i = new Intent(Cart.this, Dialog_select_outlet.class);
                    i.putExtra("message", "Cart");
                    i.putExtra("intentCart", m);
                    startActivity(i);
                }
            }
        });

        session = new SessionManager(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Items_adapter(context, moreList);
        adapter.setCustomButtonListener(Cart.this);

        Set<String> set = context.getSharedPreferences("Items", 0).getStringSet("itemNames",  new HashSet<String>());
        //Log.e("ItemsInCart", set.toString());
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
            String s =  iterator.next();
            SharedPreferences sp = context.getSharedPreferences(s,0);
            int count = sp.getInt("qty", 0);
            String price = sp.getString("price", "0");
            String pricePerKg = sp.getString("pricePerKg", "");

            if (count > 0) {
                moreList.add(new Items_structure(s, pricePerKg, price));
            }
        }

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onButtonClickListener(String itemName) {
        if (context.getSharedPreferences("Items", 0).getInt("count", 0) == 0)
            onBackPressed();
        if (itemName.equals("Remove")) {
            startActivity(getIntent());
            finish();
        }
    }

    @Override
    public void onBackPressed () {
        try {

            if (m.equals("Home")) {
                Intent i = new Intent(Cart.this, MainActivity.class);
                startActivity(i);
                finish();
            }
            else {
                Intent i = new Intent(Cart.this, Items.class);
                i.putExtra("message", n);
                startActivity(i);
                finish();
            }
        }
        catch (Exception e) {
            Intent i = new Intent(Cart.this, Items.class);
            i.putExtra("message", n);
            startActivity(i);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
