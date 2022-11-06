package com.illinois.safetyratingsuiuc;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.illinois.safetyratingsuiuc.databinding.ActivityViewRatingsBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ViewRatingsActivity extends AppCompatActivity {

    private ActivityViewRatingsBinding binding;

    // TODO How do we get this?
    private String location = "Main Quad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewRatingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(location);

        TextView expandedTitle = binding.titleLocation;
        expandedTitle.setText(location);

        Float overallRating = 4.2f;
        Float boundedRating = 3.3f;

        RatingBar overallRatingBar = binding.overallRating;
        // TODO add correct value
        overallRatingBar.setRating(overallRating);

        TextView overallRatingNum = binding.overallRatingNum;
        overallRatingNum.setText(overallRating.toString());

        View ratingsLayout = findViewById(R.id.ratings_content_layout);

        // TODO create listener for changes
        BarChart barChart = ratingsLayout.findViewById(R.id.ratings_graph);
        setGraphContents(barChart);

        // TODO create listener for changes
        Spinner timeDropdown = ratingsLayout.findViewById(R.id.filter_time_select);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getXAxisValues());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeDropdown.setAdapter(adapter);

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

    private ArrayList<Review> getReviewData() {
        ArrayList reviewList = new ArrayList();
        for(int i = 1; i < 6; i++) {
            reviewList.add(new Review("Main Quad", "October " + i + ", 2022", "12pm-1pm", i, "Test Comment " + i));
        }

        return reviewList;
    }


}