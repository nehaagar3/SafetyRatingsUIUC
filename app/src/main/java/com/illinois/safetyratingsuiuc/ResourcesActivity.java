package com.illinois.safetyratingsuiuc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

public class ResourcesActivity extends AppCompatActivity {

    // Create attributes for the navigation drawer and toggle button at the top right
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

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