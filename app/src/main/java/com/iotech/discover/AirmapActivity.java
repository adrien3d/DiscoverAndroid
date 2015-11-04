package com.iotech.discover;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by adrien on 22/10/2015.
 */
public class AirmapActivity extends FragmentActivity {

    private AirMapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airmap);

        map = (AirMapView) findViewById(R.id.map_view);
        map.initialize(this.getSupportFragmentManager());
        LatLng lens = new LatLng(50.4351469, 2.82351449);

        map.addMarker(new AirMapMarker.Builder()
                .id(0)
                .position(lens)
                .title("IG2i")
                .iconId(R.drawable.ic_favorite_tab)
                .build());
    }
}
