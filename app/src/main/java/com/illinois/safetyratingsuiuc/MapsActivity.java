package com.illinois.safetyratingsuiuc;

import static java.security.AccessController.getContext;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.illinois.safetyratingsuiuc.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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