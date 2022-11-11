package com.illinois.safetyratingsuiuc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class ResourcesActivity extends AppCompatActivity {

    // Create attributes for the navigation drawer and toggle button at the top right
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Two strings required to describe the 'open' and 'close' actions for accessibility
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        // Lets the user see the 'back' navigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Sets up which fragment (tab) to display when first opening the resources page
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Add the 'home' fragment into the layout container
        fragmentTransaction.add(R.id.main_container, new AboutUsFragment());

        // Commit the fragment that is displayed
        fragmentTransaction.commit();

        // Set the title of the toolbar to be displayed, corresponds to what fragment is displayed
        getSupportActionBar().setTitle("About us");

        navigationView = (NavigationView) findViewById(R.id.nav_menu);

        // Click listener for navigation view
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_about:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SafeWalksFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("About Fragment");
                        item.setChecked(true);
                        break;

                    case R.id.nav_safewalks:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SafeWalksFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Safe Walks Fragment");
                        item.setChecked(true);
                        break;

                    case R.id.nav_saferides:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SafeRidesFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Safe Rides Fragment");
                        item.setChecked(true);
                        break;

                    case R.id.nav_uipd:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PoliceFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Police Fragment");
                        item.setChecked(true);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Invoked whenever an item in the options menu is selected.
     * @param item The menu item that is selected -- cannot be null
     * @return false, if we want to allow menu processing to continue, true otherwise
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}