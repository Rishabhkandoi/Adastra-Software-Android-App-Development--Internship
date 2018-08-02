package com.example.hp.chhabras;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 14-06-2018.
 */

public class Login extends AppCompatActivity {

    EditText mobile;
    Button login;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobile = (EditText) findViewById(R.id.MobileNumber);
        login = (Button) findViewById(R.id.login);
        session = new SessionManager(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check_Number();
            }
        });
    }

    void Check_Number() {
        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Please Wait..");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();
        StringRequest req = new StringRequest(StringRequest.Method.POST, "http://192.168.1.105/BNI/processLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parse(response);
                Log.e("response", response);
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile.getText().toString());
                Log.e("params", "" + params);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(req);
    }

    void parse(String response) {
        try {

            JSONObject j = new JSONObject(response);
            Log.d("Login.this", j.toString());

            if (j.getString("status").equals("success")) {
                JSONObject login = new JSONObject(j.getString("login"));
                session.createUserLoginSession(login.getString("id"), login.getString("mobile"), login.getString("name"), login.getString("company"), login.getString("give"), login.getString("ask"), login.getString("total_give"), login.getString("total_ask"), login.getString("email"), login.getString("designation"));
                //Intent in = new Intent(Login.this, MainActivity.class);
                //startActivity(in);
                //finish();
                Bundle bundle = getIntent().getExtras(); String message = bundle.getString("message");
                if (message.equals("Home"))
                {
                    Intent in = new Intent(Login.this, MainActivity.class);
                    startActivity(in);
                    finish();
                }
                else if (message.equals("Items")) {
                    Intent in = new Intent(Login.this, Items.class);
                    startActivity(in);
                    finish();
                }


            } else {
                Toast.makeText(Login.this, j.getString("status") + "", Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
