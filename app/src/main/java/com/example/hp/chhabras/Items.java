package com.example.hp.chhabras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.chhabras.Adapter.Items_adapter;
import com.example.hp.chhabras.Adapter.Outlet_adapter;
import com.example.hp.chhabras.Model.Dialog_select_outlet_structure;
import com.example.hp.chhabras.Model.Items_structure;
import com.example.hp.chhabras.Util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 28-06-2018.
 */

public class Items extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    LinearLayout bottomCart, footerCart;
    TextView footerQty, footerPrice;
    String m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_items);

        Bundle bundle = getIntent().getExtras();
        m = bundle.getString("message");

        bottomCart = (LinearLayout) findViewById(R.id.bottomCartNavigation);
        footerCart = (LinearLayout) findViewById(R.id.ViewCartFooter);
        footerPrice = (TextView) findViewById(R.id.footerPrice);
        footerQty = (TextView) findViewById(R.id.footerQty);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //if (tabLayout.getTabAt(0).isSelected()) {
          //  tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        //}
    }
    
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "One");
        adapter.addFragment(new TwoFragment(), "Two");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.cart_items) {
//            Toast.makeText(Items.this, "Cart clicked", Toast.LENGTH_LONG).show();
//            return true;
//        }
//        else
        if (id == R.id.search_items) {
            Toast.makeText(Items.this, "Search clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (m.equals("Home")) {
            Intent i = new Intent(Items.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Intent i = new Intent(Items.this, Outlet.class);
            startActivity(i);
            finish();
        }
    }
}