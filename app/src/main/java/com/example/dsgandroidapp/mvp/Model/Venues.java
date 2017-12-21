package com.example.dsgandroidapp.mvp.Model;

import java.io.Serializable;

/**
 * Created by Srinivas on 12/20/2017.
 */

public class Venues implements Serializable {
    private String id;
    private String name;
    private boolean verified;
    private String url;
    private String ratingColor;
    private String ratingSignals;
    private String rating;
    private String storeId;
    private VenuesResponseVenuesLocation location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getRatingSignals() {
        return ratingSignals;
    }

    public void setRatingSignals(String ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public boolean isVerified() {
        return verified;
    }

    public VenuesResponseVenuesLocation getLocation() {
        return location;
    }

    public void setLocation(VenuesResponseVenuesLocation location) {
        this.location = location;
    }
}
