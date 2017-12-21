package com.example.dsgandroidapp.ApplicationActivity;

import android.app.Application;

import com.example.dsgandroidapp.dependenctInjection.component.ApplicationComponent;
import com.example.dsgandroidapp.dependenctInjection.component.DaggerApplicationComponent;
import com.example.dsgandroidapp.dependenctInjection.module.ApplicationModule;


/**
 * Created by Srinivas on 12/20/2017.
 */

public class VenueApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, "https://movesync-qa.dcsg.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
