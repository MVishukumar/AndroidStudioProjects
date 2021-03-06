package com.example.vishukumar.diaryapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BarChartResultPage  extends AppCompatActivity implements OnChartValueSelectedListener,
        AdapterView.OnItemSelectedListener {

    BarChart barChart, barChart2;

    RecyclerView recyclerView;
    DiaryStatusAdapter diaryStatusAdapter;

    List<DiaryStatus> diaryStatusList;

    ScrollView scrollView;

    StatusDatabaseHelper statusDatabaseHelper;
    ArrayList<BarEntry> barEntries;

    int happyCount,
            sadCount,
            angryCount,
            joyfulCount,
            motivatedCount,
            confusedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_result_page);

        //Init all the count variable values
        happyCount = 0;
        sadCount = 0;
        angryCount = 0;
        joyfulCount = 0;
        motivatedCount = 0;
        confusedCount = 0;

        //Read data from database
        statusDatabaseHelper = new StatusDatabaseHelper(this);

        Cursor cursor = statusDatabaseHelper.getAllEmotionsCount();
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "Printing entries from Database");
            while (cursor.moveToNext()) {
                String moodFromDbQuery = "";
                moodFromDbQuery = cursor.getString(0);
                Log.d("tag", "Mood From DB Query : " + moodFromDbQuery);
                switch (moodFromDbQuery.toUpperCase()) {
                    case "HAPPY":
                        happyCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + happyCount);
                        break;
                    case "SAD":
                        sadCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + sadCount);
                        break;
                    case "ANGRY":
                        angryCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + angryCount);
                        break;
                    case "JOYFUL":
                        joyfulCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + joyfulCount);
                        break;
                    case "MOTIVATED":
                        motivatedCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + motivatedCount);
                        break;
                    case "CONFUSED":
                        confusedCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + confusedCount);
                        break;
                }
                Log.d("tag", "\n");
            }
        }

        scrollView = (ScrollView) findViewById(R.id.barChartMainScrollViewId);
        scrollView.smoothScrollTo(0,0);

        barChart = (BarChart) findViewById(R.id.id_bargraph);
        barChart.setOnChartValueSelectedListener(this);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(100);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.getDescription().setEnabled(false);


        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, (float) happyCount));
        barEntries.add(new BarEntry(1, (float) sadCount));
        barEntries.add(new BarEntry(2, (float) angryCount));
        barEntries.add(new BarEntry(3, (float) joyfulCount));
        barEntries.add(new BarEntry(4, (float) motivatedCount));
        barEntries.add(new BarEntry(5, (float) confusedCount));

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(barDataSet);
        //barData.setBarWidth(0.9f);

        barChart.setData(barData);


        String[] months = new String[] {
                "Happy", "Sad", "Angry", "Joyful", "Motivated", "Confused"
        };

        YAxis yAxis = barChart.getAxisRight();
        yAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(new MyXvalueFormatter(months));

        //xAxis.setAxisMinimum(1);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        //For displaying all the status
        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
        cursor = statusDatabaseHelper.getAllStatus();
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "***********Printing entries from Database");
            String dbDate, dbStatus, dbMood;
            while (cursor.moveToNext()) {
                dbDate = cursor.getString(1);
                dbStatus = cursor.getString(2);
                dbMood = cursor.getString(3);

                diaryStatusList.add(new DiaryStatus(dbDate, dbMood, dbStatus));

                Log.d("tag", dbDate);
                Log.d("tag", dbStatus);
                Log.d("tag", dbMood);
                Log.d("tag", "\n");
            }
        }

        diaryStatusAdapter = new DiaryStatusAdapter(this, diaryStatusList);


        recyclerView.setAdapter(diaryStatusAdapter);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.d("tag", "onValueSelected for chart");
        String onTouchMood = "";
        switch (barEntries.indexOf(e)) {
            case 0:
                onTouchMood = "Happy";
                Log.d("tag", "Happy");
                break;
            case 1:
                onTouchMood = "Sad";
                Log.d("tag", "Sad");
                break;
            case 2:
                onTouchMood = "Angry";
                Log.d("tag", "Angry");
                break;
            case 3:
                onTouchMood = "Joyful";
                Log.d("tag", "Joyful");
                break;
            case 4:
                onTouchMood = "Motivated";
                Log.d("tag", "Motivated");
                break;
            case 5:
                onTouchMood = "Confused";
                Log.d("tag", "Confused");
                break;
        }

        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
        Cursor cursor = statusDatabaseHelper.getAllStatus(onTouchMood);
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "***********Printing entries from Database");
            String dbDate, dbStatus, dbMood;
            while (cursor.moveToNext()) {
                dbDate = cursor.getString(1);
                dbStatus = cursor.getString(2);
                dbMood = cursor.getString(3);

                diaryStatusList.add(new DiaryStatus(dbDate, dbMood, dbStatus));

                Log.d("tag", dbDate);
                Log.d("tag", dbStatus);
                Log.d("tag", dbMood);
                Log.d("tag", "\n");
            }
        }

        diaryStatusAdapter = new DiaryStatusAdapter(this, diaryStatusList);


        recyclerView.setAdapter(diaryStatusAdapter);
    }

    @Override
    public void onNothingSelected() {
        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
        Cursor cursor = statusDatabaseHelper.getAllStatus();
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "***********Printing entries from Database");
            String dbDate, dbStatus, dbMood;
            while (cursor.moveToNext()) {
                dbDate = cursor.getString(1);
                dbStatus = cursor.getString(2);
                dbMood = cursor.getString(3);

                diaryStatusList.add(new DiaryStatus(dbDate, dbMood, dbStatus));

                Log.d("tag", dbDate);
                Log.d("tag", dbStatus);
                Log.d("tag", dbMood);
                Log.d("tag", "\n");
            }
        }

        diaryStatusAdapter = new DiaryStatusAdapter(this, diaryStatusList);


        recyclerView.setAdapter(diaryStatusAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //Log.d("tag", allMonths[position] + " ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
