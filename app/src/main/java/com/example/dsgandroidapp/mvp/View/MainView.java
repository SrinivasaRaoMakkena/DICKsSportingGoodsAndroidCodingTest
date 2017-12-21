package com.example.dsgandroidapp.mvp.View;


import com.example.dsgandroidapp.mvp.Model.Venues;
import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenues;

import java.util.List;

/**
 * Created by Srinivas on 12/20/2017.
 */

public interface MainView extends BaseView {
    void onVenueLoaded(List<VenuesResponseVenues> venues);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
