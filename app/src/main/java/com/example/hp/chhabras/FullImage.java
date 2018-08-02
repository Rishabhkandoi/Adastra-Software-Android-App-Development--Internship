package com.example.hp.chhabras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by hp on 27-06-2018.
 */

public class FullImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_image_view);

        int imgid = getIntent().getIntExtra("IMAGE", 0);
        ImageView img = (ImageView)findViewById(R.id.fullImage);
        img.setImageResource(imgid);
    }
}