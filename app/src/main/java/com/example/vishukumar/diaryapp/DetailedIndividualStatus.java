package com.example.vishukumar.diaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedIndividualStatus extends AppCompatActivity {

    String mDate, mMood, mDesc;

    ImageView imageView;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_individual_status);

        Bundle bundle = getIntent().getExtras();
        mDate = bundle.getString("_DATE");
        mMood = bundle.getString("_MOOD");
        mDesc = bundle.getString("_DESC");



        imageView = (ImageView) findViewById(R.id.imageView3);
        textView1 = (TextView) findViewById(R.id.id_tv_date);
        textView2 = (TextView) findViewById(R.id.id_tv_mood);
        textView3 = (TextView) findViewById(R.id.id_tv_desc);

        textView1.setText(mDate);
        textView2.setText(mMood);
        textView3.setText(mDesc);

        switch (mMood.toUpperCase()) {
            case "HAPPY": imageView.setImageResource(R.drawable.happy);  break;
            case "SAD": imageView.setImageResource(R.drawable.sad); break;
            case "ANGRY": imageView.setImageResource(R.drawable.angry); break;
            case "JOYFUL": imageView.setImageResource(R.drawable.joyful); break;
            case "MOTIVATED": imageView.setImageResource(R.drawable.motivated); break;
            case "CONFUSED": imageView.setImageResource(R.drawable.confused); break;
        }

    }
}
