package com.example.dsgandroidapp.mvp.Model;

import java.io.Serializable;

public class VenuesResponseVenuesPhotos implements Serializable {
    private int createdAt;
    private String photoId;
    private String url;

    public int getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
