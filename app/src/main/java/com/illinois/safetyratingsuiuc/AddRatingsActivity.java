package com.illinois.safetyratingsuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class AddRatingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static final String[] times = new String[]{"12 AM - 3 AM", "3 AM - 6 AM", "6 AM - 9 AM", "9 AM - 12 PM", "12 PM - 3 PM", "3 PM - 6 PM", "6 PM - 9 PM", "9 PM - 12 AM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ratings);
        //get the spinner from the xml.
        getSupportActionBar().setTitle("Main Quad"); // TODO: pass as intent
        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        EditText nameEditText = (EditText) findViewById(R.id.comment);
        String comment = nameEditText.getText().toString();
        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.rating);
        Button submitButton = (Button) findViewById(R.id.submitReviewButton);
        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();
                if (comment.trim().equals("")) {
                    nameEditText.setError("Comment is required"); // TODO: better error message?
                }
                // TODO: make reviews object and add to location
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}