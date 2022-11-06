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

    // TODO How do we get this?
    private String location = "Main Quad";
    private Float overallRating = 4.2f;
    private Float boundedRating = 3.3f;

    // TODO Move to consts?
    private ArrayList<String> chartXAxis;
    private ArrayList<String> timeIntervals;

    // Screen components
    Spinner timeDropdown;
    BarChart reviewChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chartXAxis = new ArrayList<>(Arrays.asList(
                "12-3am", "3-6am", "6-9am", "9am-12pm", "12-3pm", "3-6pm", "6-9pm", "9pm-12am"));
        timeIntervals = new ArrayList(chartXAxis);
        timeIntervals.add(0, "All time");

        binding = ActivityViewRatingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(location);

        TextView expandedTitle = binding.titleLocation;
        expandedTitle.setText(location);

        RatingBar overallRatingBar = binding.overallRating;
        // TODO add correct value
        overallRatingBar.setRating(overallRating);

        TextView overallRatingNum = binding.overallRatingNum;
        overallRatingNum.setText(overallRating.toString());

        View ratingsLayout = findViewById(R.id.ratings_content_layout);

        // TODO create listener for changes
        reviewChart = ratingsLayout.findViewById(R.id.ratings_graph);
        setGraphContents(reviewChart);

        // TODO create listener for changes
        timeDropdown = ratingsLayout.findViewById(R.id.filter_time_select);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, timeIntervals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeDropdown.setAdapter(adapter);
        timeDropdown.setOnItemSelectedListener(timeDropdownListener);

        TextView timeBoundedRatingNum = ratingsLayout.findViewById(R.id.time_bounded_rating_num);
        timeBoundedRatingNum.setText(boundedRating.toString());

        RatingBar timeBoundedRatingBar = ratingsLayout.findViewById(R.id.time_bounded_rating);
        timeBoundedRatingBar.setRating(boundedRating);

        RecyclerView reviewRV = ratingsLayout.findViewById(R.id.review_rv);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, getReviewData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewRV.setLayoutManager(linearLayoutManager);
        reviewRV.setAdapter(reviewAdapter);
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

    // TODO temporary
    private ArrayList<Review> getReviewData() {
        ArrayList reviewList = new ArrayList();
        for(int i = 1; i < 6; i++) {
            reviewList.add(new Review("Main Quad", "October " + i + ", 2022", "12pm-1pm", i, "Test Comment " + i));
        }

        return reviewList;
    }

    // TODO
    private void showReviews() {}

    // Review graph listener
    OnChartValueSelectedListener chartListener =
        new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int selection = (int) e.getX() + 1;
                timeDropdown.setSelection(selection, true);
                showReviews();
            }

            @Override
            public void onNothingSelected() {
                timeDropdown.setSelection(0, true);
                showReviews();
            }
        };

    // Time dropdown change listener
    AdapterView.OnItemSelectedListener timeDropdownListener =
        new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (selection.equals(timeIntervals.get(0))) {
                    reviewChart.highlightValues(null);
                } else {
                    reviewChart.highlightValue(position - 1, 0);
                }

                showReviews();

            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {}
        };
}