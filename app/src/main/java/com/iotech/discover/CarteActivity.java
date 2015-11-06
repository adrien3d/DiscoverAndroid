package com.iotech.discover;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by adrien on 22/10/2015.
 */
public class CarteActivity extends Activity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        String ville = getIntent().getStringExtra("ville");
        LatLng london = new LatLng(51.507351, -0.127758);
        LatLng paris = new LatLng(48.856614, 2.3522219);
        LatLng lille = new LatLng(50.633333, 3.066667);
        LatLng lens = new LatLng(50.4351469, 2.82351449);

        map.setMyLocationEnabled(true);
        System.out.println("|"+ville+"|");
        if (ville.contains("London")) map.moveCamera(CameraUpdateFactory.newLatLngZoom(london, 12));
        else if (ville.contains("Paris")) map.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, 11));
        else if (ville.contains("Lille")) map.moveCamera(CameraUpdateFactory.newLatLngZoom(lille, 13));
        else if (ville.contains("Lens")) map.moveCamera(CameraUpdateFactory.newLatLngZoom(lens, 13));


        map.addMarker(new MarkerOptions().title("Londres").snippet("La capitale britanique").position(london));
        map.addMarker(new MarkerOptions().title("Paris").snippet("La capitale de la France").position(paris));
        map.addMarker(new MarkerOptions().title("Lille").snippet("La capitale des Flandres").position(lille));
        map.addMarker(new MarkerOptions().title("Lens").snippet("La capitale de IG2i").position(lens));
    }

}
