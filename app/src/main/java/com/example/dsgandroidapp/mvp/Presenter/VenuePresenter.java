package com.example.dsgandroidapp.mvp.Presenter;


import com.example.dsgandroidapp.api.VenueApiService;
import com.example.dsgandroidapp.base.BasePresenter;
import com.example.dsgandroidapp.mapper.VenueMapper;
import com.example.dsgandroidapp.mvp.Model.Venues;
import com.example.dsgandroidapp.mvp.Model.VenuesResponse;
import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenues;
import com.example.dsgandroidapp.mvp.View.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by Srinivas on 12/20/2017.
 */

public class VenuePresenter extends BasePresenter<MainView> implements Observer<VenuesResponse> {

    @Inject
    protected VenueApiService mApiService;
    @Inject
    protected VenueMapper mVenueMapper;

    @Inject
    public VenuePresenter() {

    }

    public void getVenues() {
        getView().onShowDialog("Loading Venues....");
        Observable<VenuesResponse> venuesResponseObservable = mApiService.getVenues();
        subscribe(venuesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Venues loading complete!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading Venues " + e.getMessage());
    }

    @Override
    public void onNext(VenuesResponse venuesResponse) {
        List<VenuesResponseVenues> venues = mVenueMapper.mapVenues(venuesResponse);
        getView().onVenueLoaded(venues);
    }
}
