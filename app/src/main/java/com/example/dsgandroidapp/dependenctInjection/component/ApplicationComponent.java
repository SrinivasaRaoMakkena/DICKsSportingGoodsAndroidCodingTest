package com.example.dsgandroidapp.dependenctInjection.component;

import android.content.Context;


import com.example.dsgandroidapp.dependenctInjection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Srinivas on 12/20/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Retrofit exposeRetrofit();

    Context exposeContext();
}
