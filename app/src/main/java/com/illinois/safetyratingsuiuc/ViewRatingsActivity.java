package com.illinois.safetyratingsuiuc;

import android.media.Rating;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RatingBar;

import com.illinois.safetyratingsuiuc.databinding.ActivityViewRatingsBinding;

import java.util.ArrayList;

public class ViewRatingsActivity extends AppCompatActivity {

    private ActivityViewRatingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewRatingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        // TODO update toolbar title
        toolBarLayout.setTitle("Main Quad");

        RatingBar ratingBar = binding.overallRating;
        // TODO add correct value
        ratingBar.setRating(4);



        View ratingsLayout = findViewById(R.id.ratings_content_layout);
        BarChart barChart = ratingsLayout.findViewById(R.id.ratings_graph);
        setGraphContents(barChart);

    }

    private void setGraphContents(BarChart chart) {
        //BarChart barChart = binding.ratingsGraph;
        BarData data = new BarData(getDataSet());

        // Don't label each bar with numerical data
        data.setDrawValues(false);

        chart.setData(data);

        chart.getDescription().setEnabled(false);

        chart.getAxisRight().setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(5);
        yAxis.setAxisMinimum(0);
        yAxis.setGranularity(1);



        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisValues()));

        // X axis values on bottom of chart
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        // Remove grid lines
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);

        chart.getLegend().setEnabled(false);

        chart.animateY(2000);
    }


    private BarDataSet getDataSet() {
        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        ArrayList barEntriesArrayList = new ArrayList<>();

        // TODO add real data
        barEntriesArrayList.add(new BarEntry(0f, 1f));
        barEntriesArrayList.add(new BarEntry(1f, 1.5f));
        barEntriesArrayList.add(new BarEntry(2f, 2f));
        barEntriesArrayList.add(new BarEntry(3f, 2.5f));
        barEntriesArrayList.add(new BarEntry(4f, 3f));
        barEntriesArrayList.add(new BarEntry(5f, 3.5f));
        barEntriesArrayList.add(new BarEntry(6f, 4f));
        barEntriesArrayList.add(new BarEntry(7f, 4.5f));


        BarDataSet barDataSet1 = new BarDataSet(barEntriesArrayList, "Stars");

        return barDataSet1;
    }

    // TODO add to consts?
    private ArrayList getXAxisValues() {
        ArrayList xAxis = new ArrayList();
        xAxis.add("12am-3am");
        xAxis.add("3am-6am");
        xAxis.add("6am-9am");
        xAxis.add("9am-12pm");
        xAxis.add("12pm-3pm");
        xAxis.add("3pm-6pm");
        xAxis.add("6pm-9pm");
        xAxis.add("9pm-12pm");
        return xAxis;
    }

}