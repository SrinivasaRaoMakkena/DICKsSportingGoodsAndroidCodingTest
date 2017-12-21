package com.example.dsgandroidapp.mapper;


import com.example.dsgandroidapp.mvp.Model.Venues;
import com.example.dsgandroidapp.mvp.Model.VenuesResponse;
import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenues;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Srinivas on 12/20/2017.
 */

public class VenueMapper {
    @Inject
    public VenueMapper() {

    }

    public List<VenuesResponseVenues> mapVenues(VenuesResponse response) {
        List<VenuesResponseVenues> venuesList = new ArrayList<>();

        if (response != null) {
            VenuesResponseVenues[] venuesResponseVenues = response.getVenues();
            if (venuesResponseVenues != null) {
                for (VenuesResponseVenues venues : venuesResponseVenues) {
                    venuesList.add(venues);
                }
            }
        }

        return venuesList;
    }


}
