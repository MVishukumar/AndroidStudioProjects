package com.example.vishukumar.screendesign;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //How are you feeling today title
    TextView title, subTitle;
    Typeface robotoNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.idTvTitle);
        robotoNormal = Typeface.createFromAsset(getAssets(), "roboto.regular.ttf");
        title.setTypeface(robotoNormal);

        subTitle = (TextView) findViewById(R.id.idTvSubquestion);
        subTitle.setTypeface(robotoNormal);

    }
}
