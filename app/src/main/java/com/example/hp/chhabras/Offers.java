package com.example.hp.chhabras;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Offers extends AppCompatActivity {
    public String name, searchString;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> dummy = new ArrayList<>();
    String userNames[];
    MyAdapter adapter;
    ListView listview;
    EditText search;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        StringRequest req = new StringRequest(StringRequest.Method.POST, "http://192.168.1.105/test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parse(response);
                Log.e("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        Volley.newRequestQueue(this).add(req);
    }

    void parse(String response) {
        try {

            JSONArray arr = new JSONArray(response);
            int count = 1;
            for (int i = 0; i < arr.length();i++, count++)
            {
                JSONObject obj = arr.getJSONObject(i);

                list.add(obj.getString("Name"));
                dummy.add(obj.getString("Name"));
            }
            System.out.println(list);

            listview = (ListView)findViewById(R.id.listview3);
            userNames = list.toArray(new String[0]);

            adapter = new MyAdapter(Offers.this,list);

            listview.setAdapter(adapter);
            listview.setTextFilterEnabled(true);

            b = (Button) findViewById(R.id.SearchButton);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    search = (EditText) findViewById(R.id.StudentName);
                    adapter.filter(search.getText().toString());
                }
            });

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    System.out.println(position);
//                    Intent i = new Intent(viewprofile.this, profileDisplay.class);
//                    i.putExtra("name",list.get(position));
//                    startActivity(i);
                    Toast.makeText(Offers.this, "Clicked", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(Offers.this, "Oops !", Toast.LENGTH_LONG).show();
        }
    }

    public class MyAdapter extends BaseAdapter {
        Context context;
        List<String> rowData;

        public MyAdapter(Context context, List<String> items) {
            this.context = context;
            this.rowData = items;
        }
        /*private view holder class*/
        private class ViewHolder {
            TextView txtTitle;

        }
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.custom_row, null);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView.findViewById(R.id.listDisplayRow);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            String rowItem = getItem(position);
            System.out.println(rowItem);
            holder.txtTitle.setText(rowItem);
            return convertView;
        }
        @Override
        public int getCount() {
            return rowData.size();
        }
        @Override
        public String getItem(int position) {
            return rowData.get(position);
        }
        @Override
        public long getItemId(int position) {
            return rowData.indexOf(getItem(position));
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            list.clear();

            if (charText.length() == 0) {
                list.addAll(dummy);

            } else {

                for (String wp : dummy) {

                    if (wp.toLowerCase(Locale.getDefault()).contains(charText) ) {
                        list.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }
}