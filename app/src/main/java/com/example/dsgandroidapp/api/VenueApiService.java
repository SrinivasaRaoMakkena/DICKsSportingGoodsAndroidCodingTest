package com.example.dsgandroidapp.api;


import com.example.dsgandroidapp.mvp.Model.VenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Srinivas on 12/20/2017.
 */

public interface VenueApiService {


    @GET("/dsglabs/mobile/api/venue/")
    Observable<VenuesResponse> getVenues();

}
