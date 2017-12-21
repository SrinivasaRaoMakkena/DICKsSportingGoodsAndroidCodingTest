package com.example.dsgandroidapp.mvp.Model;

import java.io.Serializable;

public class VenuesResponseVenues implements Serializable {
    private int ratingSignals;
    private String name;
    private boolean verified;
    private String ratingColor;
    private double rating;
    private VenuesResponseVenuesLocation location;
    private String id;
    private String storeId;
    private VenuesResponseVenuesPhotos[] photos;
    private String url;
    private VenuesResponseVenuesContacts[] contacts;

    public int getRatingSignals() {
        return this.ratingSignals;
    }

    public void setRatingSignals(int ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getRatingColor() {
        return this.ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public VenuesResponseVenuesLocation getLocation() {
        return this.location;
    }

    public void setLocation(VenuesResponseVenuesLocation location) {
        this.location = location;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public VenuesResponseVenuesPhotos[] getPhotos() {
        return this.photos;
    }

    public void setPhotos(VenuesResponseVenuesPhotos[] photos) {
        this.photos = photos;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VenuesResponseVenuesContacts[] getContacts() {
        return this.contacts;
    }

    public void setContacts(VenuesResponseVenuesContacts[] contacts) {
        this.contacts = contacts;
    }
}
