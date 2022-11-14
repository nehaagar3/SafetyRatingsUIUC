package com.illinois.safetyratingsuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddRatingsActivity extends AppCompatActivity {
    private String formattedDate = "";
    private int position = 0;
    private String comment = "";
    private float rating = 0;
    private String time = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ratings);
        //get the spinner from the xml.
        Bundle b = getIntent().getExtras();
        String location = b.getString(Constants.LOCATION_ACTVITY_PARAM_KEY);
        getSupportActionBar().setTitle(location);

        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Constants.timeStrings);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                time = Constants.timeStrings.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button submitButton = (Button) findViewById(R.id.submitReviewButton);
        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                EditText nameEditText = (EditText) findViewById(R.id.comment);
                comment = nameEditText.getText().toString();
                RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.rating);
                rating = simpleRatingBar.getRating();
                DatePicker datePicker = findViewById(R.id.datePicker);
                long dateTime = datePicker.getCalendarView().getDate();
                Date date = new Date(dateTime);
                DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
                formattedDate = dateFormat.format(date);
                if (comment.trim().equals("")) {
                    nameEditText.setError("This is required");
                }
                else {
                    // TODO: make reviews object and add to location
                    Review review = new Review(location, formattedDate, time, rating, comment);
                    Globals.reviewData.getReviewLocation(location).addReview(position, review); // timeInterval: index in time interval list
                    Intent intent = new Intent(AddRatingsActivity.this, ViewRatingsActivity.class);
                    Bundle b = new Bundle();
                    b.putString(Constants.LOCATION_ACTVITY_PARAM_KEY, location); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


}