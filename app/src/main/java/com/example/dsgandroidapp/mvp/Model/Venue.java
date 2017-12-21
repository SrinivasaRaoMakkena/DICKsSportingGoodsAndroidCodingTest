package com.example.dsgandroidapp.mvp.Model;

import java.io.Serializable;

/**
 * Created by Srinivas on 12/20/2017.
 */

public class Venue implements Serializable {
    private double distance;
    private VenuesResponseVenues venue;
    private boolean favourite;

    public Venue(double distance, VenuesResponseVenues venue, boolean favourite) {
        this.distance = distance;
        this.venue = venue;
        this.favourite = favourite;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public VenuesResponseVenues getVenue() {
        return venue;
    }

    public void setVenue(VenuesResponseVenues venue) {
        this.venue = venue;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
