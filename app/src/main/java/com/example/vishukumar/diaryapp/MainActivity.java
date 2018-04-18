package com.example.vishukumar.diaryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Typeface grobold;
    Animation fadeIn, bounce;
    TextView title, subtitle;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to elements from layout xml
        title = (TextView) findViewById(R.id.id_tv_title);
        subtitle = (TextView) findViewById(R.id.id_tv_subtitle);
        imageView = (ImageView) findViewById(R.id.id_iv_main);

        // Set values to title and subtitle
        title.setText(getString(R.string.main_title));
        subtitle.setText(getString(R.string.main_subtitle));

        // Set typeface
        grobold = Typeface.createFromAsset(getAssets(), "GROBOLD.ttf");
        title.setTypeface(grobold);

        // Set Animation
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.subtitle);
        bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        title.setAnimation(bounce);
        imageView.setAnimation(fadeIn);
        subtitle.setAnimation(fadeIn);

        // Naviage to next activity after 3 seconds delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,
                        NewNoteActivity.class);
                startActivity(i);
                finish();
            }
        }, 2500);
    }
}
