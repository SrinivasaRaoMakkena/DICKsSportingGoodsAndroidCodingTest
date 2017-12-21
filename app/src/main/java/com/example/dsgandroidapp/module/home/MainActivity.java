package com.example.dsgandroidapp.module.home;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.example.dsgandroidapp.R;
import com.example.dsgandroidapp.base.ParentActivity;
import com.example.dsgandroidapp.dependenctInjection.component.DaggerVenueComponent;
import com.example.dsgandroidapp.dependenctInjection.module.VenueModule;
import com.example.dsgandroidapp.module.details.DetailsActivity;
import com.example.dsgandroidapp.module.home.adapter.VenueAdapter;
import com.example.dsgandroidapp.mvp.Model.Venue;
import com.example.dsgandroidapp.mvp.Model.Venues;
import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenues;
import com.example.dsgandroidapp.mvp.Presenter.VenuePresenter;
import com.example.dsgandroidapp.mvp.View.MainView;
import com.example.dsgandroidapp.services.GPSService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;


public class MainActivity extends ParentActivity implements MainView {

    double latitude;
    double longitude;

    @Bind(R.id.venue_list)
    protected RecyclerView mVenueList;

    @Inject
    protected VenuePresenter mPresenter;

    private VenueAdapter venueAdapter;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initializeList();
        setTitle("DSG's Venue Locations");
        mPresenter.getVenues();
        locationUpdate();
        // loadVenues();
    }

    private void initializeList() {
        mVenueList.setHasFixedSize(true);
        mVenueList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        venueAdapter = new VenueAdapter(getLayoutInflater(), MainActivity.this);

        venueAdapter.setVenueClickListener(mVenueClickListener);
        mVenueList.setAdapter(venueAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerVenueComponent.builder()
                .applicationComponent(getApplicationComponent())
                .venueModule(new VenueModule(this))
                .build().inject(this);
    }

    @Override
    public void onVenueLoaded(List<VenuesResponseVenues> venues) {
        System.out.println("size " + venues.get(0).getLocation().getAddress());
        List<Venue> venueList = new ArrayList<>();
        for (VenuesResponseVenues v : venues) {
            if (v.getLocation() != null) {
                double distance = getDistanceInMiles(latitude, longitude, v.getLocation().getLatitude(), v.getLocation().getLongitude());
                System.out.println("distance " + distance);
                Venue venue = new Venue(distance, v, false);
                venueList.add(venue);
            }
        }
        sorting(venueList);

        venueAdapter.addVenues(venueList);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClearItems() {

    }

    GPSService gps;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    gps = new GPSService(MainActivity.this, MainActivity.this);

                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(getApplicationContext(), "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void locationUpdate() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Toast.makeText(MainActivity.this, "You need have granted permission", Toast.LENGTH_SHORT).show();
            gps = new GPSService(MainActivity.this, MainActivity.this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();


                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }
    }

    public static double getDistanceInMiles(double lat1, double lon1, double lat2, double lon2) {
        double km = getDistanceInKM(lat1, lon1, lat2, lon2);
        // convert from kilometers to miles

        double distMiles = (km / 1.60934) + (double) 1.0;
        return distMiles;
    }

    public static double getDistanceInKM(double lat1, double lon1, double lat2, double lon2) {

        // computer radius in kilometers

        double dblRadius = 6371.0; // km

        double dblLat = Math.toRadians(lat2 - lat1);
        double dblLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dblLat / 2) * Math.sin(dblLat / 2) + Math.sin(dblLon / 2) * Math.sin(dblLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = dblRadius * c;

        return d;
    }

    public void sorting(List<Venue> list) {
        Collections.sort(list, new Comparator<Venue>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(Venue o1, Venue o2) {
                if (o1.isFavourite()) {
                    System.out.println(o1.isFavourite() + " " + o1.getDistance());
                    return Boolean.compare(o2.isFavourite(), o1.isFavourite());
                } else {
                    if (o1.getDistance() < o2.getDistance()) {
                        return -1;
                    } else if (o1.getDistance() > o2.getDistance()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });

    }

    private VenueAdapter.OnVenueClickListener mVenueClickListener = new VenueAdapter.OnVenueClickListener() {
        @Override
        public void onClick(Venue venue, int position) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("venue", venue);
            startActivity(intent);
        }
    };
}
