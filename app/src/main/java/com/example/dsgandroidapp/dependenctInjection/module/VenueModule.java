package com.example.dsgandroidapp.dependenctInjection.module;


import com.example.dsgandroidapp.api.VenueApiService;
import com.example.dsgandroidapp.dependenctInjection.scope.PerActivity;
import com.example.dsgandroidapp.mvp.View.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Srinivas on 12/20/2017.
 */
@Module
public class VenueModule {
    private MainView mView;

    public VenueModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    VenueApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(VenueApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}
