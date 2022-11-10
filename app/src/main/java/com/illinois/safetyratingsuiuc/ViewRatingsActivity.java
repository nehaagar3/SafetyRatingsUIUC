package com.illinois.safetyratingsuiuc;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.illinois.safetyratingsuiuc.databinding.ActivityViewRatingsBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ViewRatingsActivity extends AppCompatActivity {

    private ActivityViewRatingsBinding binding;

    // TODO Add as activity parameter
    private String location = "Main Quad";

    private ArrayList<String> chartXAxis;
    private ArrayList<String> timeIntervals;

    // Screen components
    Spinner timeDropdown;
    BarChart reviewChart;
    TextView timeBoundedRatingNum;
    RatingBar timeBoundedRatingBar;
    RatingBar overallRatingBar;
    TextView overallRatingNum;

    ReviewAdapter reviewAdapter;

    ArrayList<Review> activeReviewData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chartXAxis = Constants.timeStrings;
        timeIntervals = new ArrayList<>(Arrays.asList(
                "All time", "12-3am", "3-6am", "6-9am", "9am-12pm", "12-3pm", "3-6pm", "6-9pm", "9pm-12am"));

        binding = ActivityViewRatingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(location);

        TextView expandedTitle = binding.titleLocation;
        expandedTitle.setText(location);

        overallRatingBar = binding.overallRating;
        overallRatingNum = binding.overallRatingNum;

        View ratingsLayout = findViewById(R.id.ratings_content_layout);

        reviewChart = ratingsLayout.findViewById(R.id.ratings_graph);
        setGraphContents(reviewChart);

        timeDropdown = ratingsLayout.findViewById(R.id.filter_time_select);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, timeIntervals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeDropdown.setAdapter(adapter);
        timeDropdown.setOnItemSelectedListener(timeDropdownListener);

        timeBoundedRatingNum = ratingsLayout.findViewById(R.id.time_bounded_rating_num);
        timeBoundedRatingBar = ratingsLayout.findViewById(R.id.time_bounded_rating);

        RecyclerView reviewRV = ratingsLayout.findViewById(R.id.review_rv);
        reviewAdapter = new ReviewAdapter(this, activeReviewData);
        reviewRV.setAdapter(reviewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewRV.setLayoutManager(linearLayoutManager);

        showOverallRating();
        showReviews(0, true);
    }

    private void setGraphContents(BarChart chart) {
        chart.setOnChartValueSelectedListener(chartListener);

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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(chartXAxis));
        // X axis values on bottom of chart
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);

        // Remove grid lines
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);

        chart.getLegend().setEnabled(false);

        chart.animateY(2000);
    }

    // TODO add real data
    private BarDataSet getDataSet() {
        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        ArrayList barEntriesArrayList = new ArrayList<>();

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

//    // TODO add real data
//    private BarDataSet updateDataSet(ArrayList<ArrayList<Review>> reviewData) {
//        // adding new entry to our array list with bar
//        // entry and passing x and y axis value to it.
//        ArrayList barEntriesArrayList = new ArrayList<>();
//
//        for(int i = 0; i < reviewData.size(); i++) {
//            for
//        }
//        barEntriesArrayList.add(new BarEntry(0f, 1f));
//        barEntriesArrayList.add(new BarEntry(1f, 1.5f));
//        barEntriesArrayList.add(new BarEntry(2f, 2f));
//        barEntriesArrayList.add(new BarEntry(3f, 2.5f));
//        barEntriesArrayList.add(new BarEntry(4f, 3f));
//        barEntriesArrayList.add(new BarEntry(5f, 3.5f));
//        barEntriesArrayList.add(new BarEntry(6f, 4f));
//        barEntriesArrayList.add(new BarEntry(7f, 4.5f));
//
//
//        BarDataSet barDataSet1 = new BarDataSet(barEntriesArrayList, "Stars");
//
//        return barDataSet1;
//    }


    public void showOverallRating() {
        ArrayList<Review> data = Constants.reviewData.getReviews(0, true);

        // Avg Rating
        Float rating = 0f;
        if (data.size() != 0) {
            for(int i = 0; i < data.size(); i++) {
                rating += data.get(i).getRating();
            }
            rating /= data.size();
        }

        overallRatingNum.setText(String.format("%.2g%n", rating));
        overallRatingBar.setRating(rating);
    }

    private void showReviews(int timeInterval, boolean all) {
        ArrayList<Review> data = Constants.reviewData.getReviews(timeInterval, all);

        // Avg Rating
        Float rating = 0f;
        if (data.size() != 0) {
            for(int i = 0; i < data.size(); i++) {
                rating += data.get(i).getRating();
            }
            rating /= data.size();
        }

        timeBoundedRatingNum.setText(String.format("%.2g%n", rating));
        timeBoundedRatingBar.setRating(rating);

        // set Reviews
        activeReviewData.clear();
        activeReviewData.addAll(data);
        reviewAdapter.notifyDataSetChanged();
    }

    // Review graph listener
    OnChartValueSelectedListener chartListener =
        new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int selection = (int) e.getX() + 1;
                timeDropdown.setSelection(selection, true);
                showReviews(selection - 1, false);
            }

            @Override
            public void onNothingSelected() {
                timeDropdown.setSelection(0, true);
                showReviews(0, true);
            }
        };

    // Time dropdown change listener
    AdapterView.OnItemSelectedListener timeDropdownListener =
        new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (position == 0) {
                    reviewChart.highlightValues(null);
                    showReviews(0, true);
                } else {
                    reviewChart.highlightValue(position - 1, 0);
                    showReviews(position - 1, false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {}
        };
}