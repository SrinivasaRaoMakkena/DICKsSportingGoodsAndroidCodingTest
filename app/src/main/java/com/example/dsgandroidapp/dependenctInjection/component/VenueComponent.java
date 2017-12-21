package com.example.dsgandroidapp.dependenctInjection.component;


import com.example.dsgandroidapp.dependenctInjection.module.VenueModule;
import com.example.dsgandroidapp.dependenctInjection.scope.PerActivity;
import com.example.dsgandroidapp.module.home.MainActivity;

import dagger.Component;

/**
 * Created by Srinivas on 12/20/2017.
 */
@PerActivity
@Component(modules = VenueModule.class, dependencies = ApplicationComponent.class)
public interface VenueComponent {
    void inject(MainActivity activity);
}
