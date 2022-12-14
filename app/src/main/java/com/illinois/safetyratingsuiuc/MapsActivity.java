package com.illinois.safetyratingsuiuc;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.illinois.safetyratingsuiuc.databinding.ActivityMapsBinding;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Marker currMarker;
    public static Boolean showPopup = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String apiKey = null;
        try {
            apiKey = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("com.google.android.geo.API_KEY");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }


        // Initialize the AutocompleteSupportFragment.

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);


        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setLocationRestriction(RectangularBounds.newInstance(new LatLng(-40.06943400464524, -88.31494084238419), new LatLng(40.158178564972815, -88.16191394471619)));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                LatLng latLng = place.getLatLng();

                if (currMarker != null) currMarker.remove();
                currMarker = mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MapsActivity.this, ViewRatingsActivity.class);
                        Bundle b = new Bundle();
                        b.putString(Constants.LOCATION_ACTVITY_PARAM_KEY, place.getName()); //Your id
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        binding.getRoot().post(new Runnable() {
            @Override
            public void run() {
                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                if (showPopup) {
                    popupWindow.showAtLocation(binding.getRoot(), Gravity.CENTER, 0, 0);
                    // dismiss the popup window when touched
                    Button dismissBotton = (Button) popupView.findViewById(R.id.dismissButton);
                    dismissBotton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    showPopup = false;
                }
            }
        });

        ExtendedFloatingActionButton resourcesButton = binding.viewResourcesButton;
        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, SafetyResourcesActivity.class);
                Bundle b = new Bundle();
                b.putString("type", "map"); //Your id
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(@NonNull PointOfInterest pointOfInterest) {
                LatLng latLng = pointOfInterest.latLng;

                if (currMarker != null) currMarker.remove();
                currMarker = mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MapsActivity.this, ViewRatingsActivity.class);
                        Bundle b = new Bundle();
                        b.putString(Constants.LOCATION_ACTVITY_PARAM_KEY, pointOfInterest.name); //Your id
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        });

        // Add a marker to left&right corner of campus to create bounds
        LatLng leftCorner = new LatLng(40.116777, -88.245366);
        LatLng rightCorner = new LatLng(40.097799, -88.218471);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(leftCorner);
        builder.include(rightCorner);

        LatLngBounds bounds = builder.build();

        // bound the camera to the campus
        mMap.setLatLngBoundsForCameraTarget(bounds);

        // the camera is at Main Quad in default
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(40.107912, -88.227267) , 17.0f) );

        // remove markers
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.empty_map_style));
    }
}