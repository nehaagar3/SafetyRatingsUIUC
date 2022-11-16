package com.illinois.safetyratingsuiuc;

import android.content.Intent;
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
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.illinois.safetyratingsuiuc.databinding.ActivityViewRatingsBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ViewRatingsActivity extends AppCompatActivity {

    private ActivityViewRatingsBinding binding;

    // TODO Add as activity parameter
    private String location = "Main Quad";
    private ReviewLocation reviewLocation;

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

        Bundle b = getIntent().getExtras();
        View parentLayout = findViewById(android.R.id.content);
        try {
            boolean showSnackBar = b.getBoolean(Constants.SHOW_SNACK_BAR_KEY);
            if (showSnackBar) {
                Snackbar.make(parentLayout, "Safety Review Submitted!", Snackbar.LENGTH_LONG).show();

            }


        }
        catch(Exception e) {
            // bundle doesn't have boolean set
        }


        reviewLocation = Globals.reviewData.getReviewLocation(this.location);

        chartXAxis = new ArrayList<>(Arrays.asList(
                "12-3am", "3-6am", "6-9am", "9am-12pm", "12-3pm", "3-6pm", "6-9pm", "9pm-12am"));
        timeIntervals = new ArrayList<>(Constants.timeStrings);
        timeIntervals.add(0, "All time");

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

        FloatingActionButton addReviewButton = binding.addReviewButton;
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Route to new activity
                Intent intent = new Intent(ViewRatingsActivity.this, AddRatingsActivity.class);
                Bundle b = new Bundle();
                b.putString(Constants.LOCATION_ACTVITY_PARAM_KEY, location); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();

                // In the AddRatingsActiviy activity, access the location string as follows
                //Bundle b = getIntent().getExtras();
                //String value = b.getString(Constants.LOCATION_ACTVITY_PARAM_KEY);
            }
        });

        FloatingActionButton viewResourcesButton = binding.viewResourcesButton;
        viewResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: Route to view resources page", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        showOverallRating();
        showReviews(null);
    }

    private void setGraphContents(BarChart chart) {
        chart.setOnChartValueSelectedListener(chartListener);

        BarData data = new BarData(updateDataSet());

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

    private BarDataSet updateDataSet() {

        ArrayList<ArrayList<Review>> reviewList = reviewLocation.getReviewData();

        ArrayList barEntriesArrayList = new ArrayList<>();

        for(int i = 0; i < reviewList.size(); i++) {
            Float rating = getAverageRating(reviewList.get(i));
            barEntriesArrayList.add(new BarEntry((float) i, rating));
        }

        BarDataSet barDataSet1 = new BarDataSet(barEntriesArrayList, "Stars");
        int color = ContextCompat.getColor(this, R.color.blue1);
        barDataSet1.setColor(color);

        return barDataSet1;
    }


    private void showOverallRating() {
        ArrayList<Review> data = reviewLocation.getReviews(null);

        Float rating = getAverageRating(data);

        overallRatingNum.setText(String.format("%.2g%n", rating));
        overallRatingBar.setRating(rating);
    }

    private void showReviews(Integer timeInterval) {
        ArrayList<Review> data = reviewLocation.getReviews(timeInterval);

        Float rating = getAverageRating(data);

        timeBoundedRatingNum.setText(String.format("%.2g%n", rating));
        timeBoundedRatingBar.setRating(rating);

        // set Reviews
        activeReviewData.clear();
        activeReviewData.addAll(data);
        reviewAdapter.notifyDataSetChanged();
    }

    private Float getAverageRating(ArrayList<Review> data) {
        Float rating = 0f;
        if (data.size() != 0) {
            for(int i = 0; i < data.size(); i++) {
                rating += data.get(i).getRating();
            }
            rating /= data.size();
        }

        return rating;
    }

    // Review graph listener
    OnChartValueSelectedListener chartListener =
        new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int selection = (int) e.getX() + 1;
                timeDropdown.setSelection(selection, true);
                showReviews(selection - 1);
            }

            @Override
            public void onNothingSelected() {
                timeDropdown.setSelection(0, true);
                showReviews(null);
            }
        };

    // Time dropdown change listener
    AdapterView.OnItemSelectedListener timeDropdownListener =
        new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (position == 0) {
                    reviewChart.highlightValues(null);
                    showReviews(null);
                } else {
                    reviewChart.highlightValue(position - 1, 0);
                    showReviews(position - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {}
        };
}