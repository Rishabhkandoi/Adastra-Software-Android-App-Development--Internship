package com.example.hp.chhabras;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class MainActivity extends AppCompatActivity
        implements BaseSliderView.OnSliderClickListener, NavigationView.OnNavigationItemSelectedListener {

    private SliderLayout mDemoSlider;
    SessionManager session;
    LinearLayout home_overview, home_360_view, home_gallery, home_view_offers, home_contact, home_view_outlet, order_online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        session = new SessionManager(MainActivity.this);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

        if (!session.isUserLoggedIn()) {
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
        }
        else {
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);
        }

        TextSliderView textSliderView = new TextSliderView(this);
        // initialize a SliderLayout
        textSliderView
                .description("first image")
                .image(R.drawable.blurred)
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);
        mDemoSlider.addSlider(textSliderView);
        TextSliderView textSliderView1 = new TextSliderView(this);
        textSliderView1
                .description("second image")
                .image(R.drawable.blurred_other)
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);
        mDemoSlider.addSlider(textSliderView1);

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);


        home_gallery = (LinearLayout) findViewById(R.id.home_view_gallery);
        home_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Gallery.class);
                startActivity(i);
            }
        });

        home_overview = (LinearLayout) findViewById(R.id.home_overview);
        home_overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Overview.class);
                startActivity(i);
            }
        });

        home_view_outlet = (LinearLayout) findViewById(R.id.home_view_outlet);
        home_view_outlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Outlet.class);
                startActivity(i);
            }
        });

        home_contact = (LinearLayout) findViewById(R.id.home_contact);
        home_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Enquiry_Contact.class);
                startActivity(i);
            }
        });

        home_view_offers = (LinearLayout) findViewById(R.id.home_view_offers);
        home_view_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Offers.class);
                startActivity(i);
            }
        });

        home_360_view = (LinearLayout) findViewById(R.id.home_360_view);
        home_360_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
            }
        });

        order_online = (LinearLayout) findViewById(R.id.home_order_online);
        order_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(MainActivity.this, Dialog_select_outlet.class);
                //i.putExtra("mes", "Home");

                Intent i = new Intent(MainActivity.this, Items.class);
                i.putExtra("message", "Home");
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences s = getSharedPreferences("Items", 0);
            int count = s.getInt("count", 0);
            if(count>0) {
                Intent i = new Intent(MainActivity.this, Cart.class);
                i.putExtra("intentCart", "Home");
                startActivity(i);
                finish();
            }
            else {
                Intent i = new Intent(MainActivity.this, Items.class);
                startActivity(i);
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;

        switch (id) {

            case R.id.nav_home:
                // Handle the home action
                break;


            case R.id.nav_gallery:
                i = new Intent(MainActivity.this, Gallery.class);
                startActivity(i);
                break;


            case R.id.nav_overview:
                i = new Intent(MainActivity.this, Overview.class);
                startActivity(i);
                break;


            case R.id.nav_360_view:
                i = new Intent(MainActivity.this, Overview.class);
                startActivity(i);
                break;


            case R.id.nav_view_offer:
                i = new Intent(MainActivity.this, Offers.class);
                startActivity(i);
                break;


            case R.id.nav_book_table:
                if(session.isUserLoggedIn()) {
                    Toast.makeText(MainActivity.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
                }
                else {
                    i = new Intent(MainActivity.this, Login.class);
                    i.putExtra("message", "Home");
                    startActivity(i);
                }
                break;


            case R.id.nav_book_table_detail:
                if(session.isUserLoggedIn()) {
                    Toast.makeText(MainActivity.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
                }
                else {
                    i = new Intent(MainActivity.this, Login.class);
                    i.putExtra("message", "Home");
                    startActivity(i);
                }
                break;


            case R.id.nav_my_order:
                if(session.isUserLoggedIn()) {
                    Toast.makeText(MainActivity.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
                }
                else {
                    i = new Intent(MainActivity.this, Login.class);
                    i.putExtra("message", "Home");
                    startActivity(i);
                }
                break;


            case R.id.nav_order_online:
                //i = new Intent(MainActivity.this, Dialog_select_outlet.class);
                //i.putExtra("message", "Home");
                i = new Intent(MainActivity.this, Items.class);
                i.putExtra("message", "Home");
                startActivity(i);
                break;


            case R.id.nav_contact:
                i = new Intent(MainActivity.this, Enquiry_Contact.class);
                startActivity(i);
                break;


            case R.id.nav_my_account:
                if(session.isUserLoggedIn()) {
                    Toast.makeText(MainActivity.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
                }
                else {
                    i = new Intent(MainActivity.this, Login.class);
                    i.putExtra("message", "Home");
                    startActivity(i);
                }
                break;


            case R.id.nav_logout:
                session.logoutUser();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;


            case R.id.nav_feedback:
                if(session.isUserLoggedIn()) {
                    Toast.makeText(MainActivity.this, "Coming soon..!!", Toast.LENGTH_LONG).show();
                }
                else {
                    i = new Intent(MainActivity.this, Login.class);
                    i.putExtra("message", "Home");
                    startActivity(i);
                }
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
}
