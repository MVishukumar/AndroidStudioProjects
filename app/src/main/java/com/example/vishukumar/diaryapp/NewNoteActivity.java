package com.example.vishukumar.diaryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    TextView textViewQ;

    float cardRadius;
    int cardElevation;

    EditText whatHappenedEditText;

    String moodState;

    Context mContext;

    StatusDatabaseHelper statusDatabaseHelper;

    String finalQuestion = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        mContext = getApplicationContext();

        // Set card radius and elevation
        cardRadius = 18f;
        cardElevation = 8;

        // Reference all the cards
        cardView1 = (CardView) findViewById(R.id.cv_id_1);
        cardView2 = (CardView) findViewById(R.id.cv_id_2);
        cardView3 = (CardView) findViewById(R.id.cv_id_3);
        cardView4 = (CardView) findViewById(R.id.cv_id_4);
        cardView5 = (CardView) findViewById(R.id.cv_id_5);
        cardView6 = (CardView) findViewById(R.id.cv_id_6);

        // Reference All Textview
        textView1 = (TextView) findViewById(R.id.id_tv_1);
        textView2 = (TextView) findViewById(R.id.id_tv_2);
        textView3 = (TextView) findViewById(R.id.id_tv_3);
        textView4 = (TextView) findViewById(R.id.id_tv_4);
        textView5 = (TextView) findViewById(R.id.id_tv_5);
        textView6 = (TextView) findViewById(R.id.id_tv_6);
        textViewQ = (TextView) findViewById(R.id.textView9);


        // Set card radius and elevation
        setCardCornerAndElevation();
    }

    // Function to hide keyboard whenever user clicks on Main Layout
    public void hideKeyboard(View view) {
        Log.d("tag", "Hiding Keyboard");
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // This method is called whenever user clicks on card
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cardClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_id_1:
                // Save mood state
                moodState = getString(R.string.happy_text);

                // Set cardview corner and elevation
                setCardCornerAndElevation();

                cardView1.setBackgroundColor(Color.WHITE);
                cardView1.setPressed(true);
                cardView2.setBackgroundColor(Color.parseColor("#009688"));
                cardView2.setPressed(false);
                cardView3.setBackgroundColor(Color.parseColor("#009688"));
                cardView3.setPressed(false);
                cardView4.setBackgroundColor(Color.parseColor("#009688"));
                cardView4.setPressed(false);
                cardView5.setBackgroundColor(Color.parseColor("#009688"));
                cardView5.setPressed(false);
                cardView6.setBackgroundColor(Color.parseColor("#009688"));
                cardView6.setPressed(false);

                // Set textview color to black
                textView1.setTextColor(Color.BLACK);

                // Reset other textview colors to white
                textView2.setTextColor(Color.WHITE);
                textView3.setTextColor(Color.WHITE);
                textView4.setTextColor(Color.WHITE);
                textView5.setTextColor(Color.WHITE);
                textView6.setTextColor(Color.WHITE);


                break;
            case R.id.cv_id_2:
                // Save mood state
                moodState = getString(R.string.sad_text);

                // Set cardview corner and elevation
                setCardCornerAndElevation();

                cardView2.setBackgroundColor(Color.WHITE);
                cardView2.setPressed(true);

                // Reset other cards
                cardView1.setBackgroundColor(Color.parseColor("#009688"));
                cardView1.setPressed(false);
                cardView3.setBackgroundColor(Color.parseColor("#009688"));
                cardView3.setPressed(false);
                cardView4.setBackgroundColor(Color.parseColor("#009688"));
                cardView4.setPressed(false);
                cardView5.setBackgroundColor(Color.parseColor("#009688"));
                cardView5.setPressed(false);
                cardView6.setBackgroundColor(Color.parseColor("#009688"));
                cardView6.setPressed(false);

                // Set textview color to black
                textView2.setTextColor(Color.BLACK);

                // Reset other textview colors to white
                textView1.setTextColor(Color.WHITE);
                textView3.setTextColor(Color.WHITE);
                textView4.setTextColor(Color.WHITE);
                textView5.setTextColor(Color.WHITE);
                textView6.setTextColor(Color.WHITE);

                // Set cardview corner and elevation
                setCardCornerAndElevation();
                break;
            case R.id.cv_id_3:
                // Save mood state
                moodState = getString(R.string.angry_text);

                // Set cardview corner and elevation
                setCardCornerAndElevation();

                cardView3.setBackgroundColor(Color.WHITE);
                cardView3.setPressed(true);

                // Reset other cards
                cardView1.setBackgroundColor(Color.parseColor("#009688"));
                cardView1.setPressed(false);
                cardView2.setBackgroundColor(Color.parseColor("#009688"));
                cardView2.setPressed(false);
                cardView4.setBackgroundColor(Color.parseColor("#009688"));
                cardView4.setPressed(false);
                cardView5.setBackgroundColor(Color.parseColor("#009688"));
                cardView5.setPressed(false);
                cardView6.setBackgroundColor(Color.parseColor("#009688"));
                cardView6.setPressed(false);

                // Set textview color to black
                textView3.setTextColor(Color.BLACK);

                // Reset other textview colors to white
                textView2.setTextColor(Color.WHITE);
                textView1.setTextColor(Color.WHITE);
                textView4.setTextColor(Color.WHITE);
                textView5.setTextColor(Color.WHITE);
                textView6.setTextColor(Color.WHITE);

                // Set cardview corner and elevation
                setCardCornerAndElevation();
                break;
            case R.id.cv_id_4:
                // Save mood state
                moodState = getString(R.string.joyful_text);

                // Set cardview corner and elevation
                setCardCornerAndElevation();

                cardView4.setBackgroundColor(Color.WHITE);
                cardView4.setPressed(true);

                // Reset other cards
                cardView1.setBackgroundColor(Color.parseColor("#009688"));
                cardView1.setPressed(false);
                cardView3.setBackgroundColor(Color.parseColor("#009688"));
                cardView3.setPressed(false);
                cardView2.setBackgroundColor(Color.parseColor("#009688"));
                cardView2.setPressed(false);
                cardView5.setBackgroundColor(Color.parseColor("#009688"));
                cardView5.setPressed(false);
                cardView6.setBackgroundColor(Color.parseColor("#009688"));
                cardView6.setPressed(false);

                // Set textview color to black
                textView4.setTextColor(Color.BLACK);

                // Reset other textview colors to white
                textView2.setTextColor(Color.WHITE);
                textView3.setTextColor(Color.WHITE);
                textView1.setTextColor(Color.WHITE);
                textView5.setTextColor(Color.WHITE);
                textView6.setTextColor(Color.WHITE);

                // Set cardview corner and elevation
                setCardCornerAndElevation();
                break;
            case R.id.cv_id_5:
                // Save mood state
                moodState = getString(R.string.motivated_text);

                // Set cardview corner and elevation
                setCardCornerAndElevation();

                cardView5.setBackgroundColor(Color.WHITE);
                cardView5.setPressed(true);

                // Reset other cards
                cardView1.setBackgroundColor(Color.parseColor("#009688"));
                cardView1.setPressed(false);
                cardView3.setBackgroundColor(Color.parseColor("#009688"));
                cardView3.setPressed(false);
                cardView4.setBackgroundColor(Color.parseColor("#009688"));
                cardView4.setPressed(false);
                cardView2.setBackgroundColor(Color.parseColor("#009688"));
                cardView2.setPressed(false);
                cardView6.setBackgroundColor(Color.parseColor("#009688"));
                cardView6.setPressed(false);

                // Set textview color to black
                textView5.setTextColor(Color.BLACK);

                // Reset other textview colors to white
                textView2.setTextColor(Color.WHITE);
                textView3.setTextColor(Color.WHITE);
                textView4.setTextColor(Color.WHITE);
                textView1.setTextColor(Color.WHITE);
                textView6.setTextColor(Color.WHITE);

                // Set cardview corner and elevation
                setCardCornerAndElevation();
                break;
            case R.id.cv_id_6:
                // Save mood state
                moodState = getString(R.string.confused_text);

                // Set cardview corner and elevation
                setCardCornerAndElevation();

                cardView6.setBackgroundColor(Color.WHITE);
                cardView6.setPressed(true);

                // Reset other cards
                cardView1.setBackgroundColor(Color.parseColor("#009688"));
                cardView1.setPressed(false);
                cardView3.setBackgroundColor(Color.parseColor("#009688"));
                cardView3.setPressed(false);
                cardView4.setBackgroundColor(Color.parseColor("#009688"));
                cardView4.setPressed(false);
                cardView5.setBackgroundColor(Color.parseColor("#009688"));
                cardView5.setPressed(false);
                cardView2.setBackgroundColor(Color.parseColor("#009688"));
                cardView2.setPressed(false);

                // Set textview color to black
                textView6.setTextColor(Color.BLACK);

                // Reset other textview colors to white
                textView2.setTextColor(Color.WHITE);
                textView3.setTextColor(Color.WHITE);
                textView4.setTextColor(Color.WHITE);
                textView5.setTextColor(Color.WHITE);
                textView1.setTextColor(Color.WHITE);

                // Set cardview corner and elevation
                setCardCornerAndElevation();
                break;
        }

        finalQuestion = getString(R.string.what_made_you_feel) + " " +  moodState + getString(R.string.question_mark);
        textViewQ.setText(finalQuestion);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setCardCornerAndElevation() {

        cardView1.setRadius(cardRadius);
        cardView1.setCardElevation(getPixelsFromDPs(cardElevation));


        cardView2.setRadius(cardRadius);
        cardView2.setCardElevation(getPixelsFromDPs(cardElevation));


        cardView3.setRadius(cardRadius);
        cardView3.setCardElevation(getPixelsFromDPs(cardElevation));


        cardView4.setRadius(cardRadius);
        cardView4.setCardElevation(getPixelsFromDPs(cardElevation));


        cardView5.setRadius(cardRadius);
        cardView5.setCardElevation(getPixelsFromDPs(cardElevation));


        cardView6.setRadius(cardRadius);
        cardView6.setCardElevation(getPixelsFromDPs(cardElevation));

        Log.d("tag", "Card radius:" + cardRadius);
        Log.d("tag", "Card elevation:" + cardElevation);
    }

    // Custom method for converting DP/DIP value to pixels
    protected int getPixelsFromDPs(int dps){
        Resources r = mContext.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }

    // Button clicked listener function
    public void myButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_id_1:
                Log.d("tag", "Save button clicked");
                saveButtonClicked(view);
                break;
            case R.id.btn_id_2:
                Log.d("tag", "Analyse button clicked");
                break;
            case R.id.btn_id_3:
                Log.d("tag", "View Notes button clicked");
                Intent i = new Intent(NewNoteActivity.this, AllNotesActivity.class);
                startActivity(i);
                break;
        }
    }

    public void saveButtonClicked(View v) {
        Log.d("tag", "Save button was clicked");

        whatHappenedEditText = (EditText) findViewById(R.id.et_what_happened);
        String todayStatus = "";
        todayStatus = whatHappenedEditText.getText().toString();

        Snackbar snackbar;
        if(moodState == null) {
            snackbar = Snackbar
                    .make(v, "Please select your mood", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            if(todayStatus.length() == 0) {
                snackbar = Snackbar
                        .make(v, "Tell yourself what made you " + moodState + ", and write it in the box.", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                statusDatabaseHelper = new StatusDatabaseHelper(v.getContext());

                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = simpleDateFormat.format(date);
                Log.d("tag","Date formatted : " + formattedDate);
                Log.d("tag","Mood State : " + moodState);
                Log.d("tag","Status : " + todayStatus);

                int result = statusDatabaseHelper.addEntryInMyDiary(formattedDate, moodState, todayStatus);


                //Show custom dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(NewNoteActivity.this);
                View view = getLayoutInflater().inflate(R.layout.popup, null);

                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(true);
                alertDialog.show();

                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent i = new Intent(NewNoteActivity.this, NewNoteActivity.class);
                        startActivity(i);
                    }

                }, 3000L);

                Log.d("tag", "Database Insertion Id : " + result);
            }
        }




        Log.d("tag", "End of onclick");

    }
}
