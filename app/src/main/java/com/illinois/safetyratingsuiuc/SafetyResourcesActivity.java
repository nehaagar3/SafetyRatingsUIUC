package com.illinois.safetyratingsuiuc;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.illinois.safetyratingsuiuc.ui.main.SectionsPagerAdapter;
import com.illinois.safetyratingsuiuc.databinding.ActivitySafetyResourcesBinding;

public class SafetyResourcesActivity extends AppCompatActivity {

    private ActivitySafetyResourcesBinding binding;
    private String prevActivity = "";
    private String prevActivityLocation = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Bundle b = getIntent().getExtras();
            prevActivity = b.getString("type");
            prevActivityLocation = b.getString(Constants.LOCATION_ACTVITY_PARAM_KEY);

        }
        catch(Exception e) {
        }

        binding = ActivitySafetyResourcesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle(R.string.resources_app_bar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), 4);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        ExtendedFloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafetyResourcesActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (prevActivity.equals("map")) {
                    Intent intent = new Intent(SafetyResourcesActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else { // view ratings page
                    Intent intent = new Intent(SafetyResourcesActivity.this, ViewRatingsActivity.class);
                    Bundle b = new Bundle();
                    b.putString(Constants.LOCATION_ACTVITY_PARAM_KEY, prevActivityLocation); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                    finish();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

}