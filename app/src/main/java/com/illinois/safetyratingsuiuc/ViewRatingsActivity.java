package com.illinois.safetyratingsuiuc;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.illinois.safetyratingsuiuc.databinding.ActivityViewRatingsBinding;
import com.illinois.safetyratingsuiuc.databinding.ContentScrollingBinding;

import java.util.ArrayList;

public class ViewRatingsActivity extends AppCompatActivity {

    private ActivityViewRatingsBinding binding;

    private String location;
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
        location = b.getString(Constants.LOCATION_ACTVITY_PARAM_KEY);

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

//        chartXAxis = new ArrayList<>(Arrays.asList(
//                "12-3am", "3-6am", "6-9am", "9am-12pm", "12-3pm", "3-6pm", "6-9pm", "9pm-12am"));
        chartXAxis = Constants.timeStrings;
        timeIntervals = new ArrayList<>(Constants.timeStrings);
        timeIntervals.add(0, "All time");

        binding = ActivityViewRatingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView expandedTitle = binding.titleLocation;
        expandedTitle.setText(location);

        AppBarLayout appBarLayout = binding.appBar;
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    binding.titleLocation.setVisibility(View.INVISIBLE);
                    binding.overallRating.setVisibility(View.INVISIBLE);
                    binding.overallRatingNum.setVisibility(View.INVISIBLE);
                } else if (isShow) {
                    isShow = false;
                    binding.titleLocation.setVisibility(View.VISIBLE);
                    binding.overallRating.setVisibility(View.VISIBLE);
                    binding.overallRatingNum.setVisibility(View.VISIBLE);
                }
            }
        });

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

        ExtendedFloatingActionButton addReviewButton = binding.addReviewButton;
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

        ExtendedFloatingActionButton viewResourcesButton = binding.viewResourcesButton;
        viewResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewRatingsActivity.this, SafetyResourcesActivity.class);
                Bundle b = new Bundle();
                b.putString("type", "view_ratings");
                b.putString(Constants.LOCATION_ACTVITY_PARAM_KEY, location);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });

        ContentScrollingBinding scrollingBinding = binding.ratingsContentLayout;
        NestedScrollView nestedScrollView = (NestedScrollView) scrollingBinding.ratingsContentScrolling;
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY + 12 && addReviewButton.isExtended() && viewResourcesButton.isExtended()) {
                    addReviewButton.shrink();
                    viewResourcesButton.shrink();
                }

                // the delay of the extension of the FAB is set for 12 items
                if (scrollY < oldScrollY - 12 && !addReviewButton.isExtended() && !viewResourcesButton.isExtended()) {
                    addReviewButton.extend();
                    viewResourcesButton.extend();
                }

                // if the nestedScrollView is at the first item of the list then the
                // extended floating action should be in extended state
                if (scrollY == 0) {
                    addReviewButton.extend();
                    viewResourcesButton.extend();
                }

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
        // Rotate labels so they fit
        xAxis.setLabelRotationAngle(-45);

        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);

        // Remove grid lines
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);

        // Create Legend
        int mainColor = ContextCompat.getColor(this, R.color.blue1);
        int noDataColor = ContextCompat.getColor(this, R.color.grey1);
        ArrayList<LegendEntry> customLegend = new ArrayList<>();
        customLegend.add(new LegendEntry("Safety Rating",Legend.LegendForm.CIRCLE,10f,2f,null,mainColor));
        customLegend.add(new LegendEntry("No Data",Legend.LegendForm.CIRCLE,10f,2f,null,noDataColor));
        Legend legend = chart.getLegend();
        legend.setCustom(customLegend);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setDrawInside(false);

        chart.animateY(2000);
    }

    private BarDataSet updateDataSet() {

        ArrayList<ArrayList<Review>> reviewList = reviewLocation.getReviewData();

        ArrayList barEntriesArrayList = new ArrayList<>();
        ArrayList barColors = new ArrayList();

        int mainColor = ContextCompat.getColor(this, R.color.blue1);
        int noDataColor = ContextCompat.getColor(this, R.color.grey1);

        for(int i = 0; i < reviewList.size(); i++) {
            Float rating = getAverageRating(reviewList.get(i));
            if (rating == null) {
                barEntriesArrayList.add(new BarEntry((float) i, 2.5f));
                barColors.add(noDataColor);
            } else {
                barEntriesArrayList.add(new BarEntry((float) i, rating));
                barColors.add(mainColor);
            }
        }

        BarDataSet barDataSet1 = new BarDataSet(barEntriesArrayList, "Stars");
        barDataSet1.setColors(barColors);

        return barDataSet1;
    }


    private void showOverallRating() {
        ArrayList<Review> data = reviewLocation.getReviews(null);

        Float rating = getAverageRating(data);

        if (rating == null) {
            overallRatingNum.setText("");
            overallRatingBar.setRating(0);
        } else {
            overallRatingNum.setText(String.format("%.2g%n", rating));
            overallRatingBar.setRating(rating);
        }
    }

    private void showReviews(Integer timeInterval) {
        ArrayList<Review> data = reviewLocation.getReviews(timeInterval);

        Float rating = getAverageRating(data);

        if (rating == null) {
            timeBoundedRatingNum.setText("");
            timeBoundedRatingBar.setRating(0);
        } else {
            timeBoundedRatingNum.setText(String.format("%.2g%n", rating));
            timeBoundedRatingBar.setRating(rating);
        }

        // set Reviews
        activeReviewData.clear();
        activeReviewData.addAll(data);
        reviewAdapter.notifyDataSetChanged();
    }

    private Float getAverageRating(ArrayList<Review> data) {
        // So we can distinguish between theoretical 0 stars and lack of data
        if (data.size() == 0)
            return null;

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

    // when back button is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ViewRatingsActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}