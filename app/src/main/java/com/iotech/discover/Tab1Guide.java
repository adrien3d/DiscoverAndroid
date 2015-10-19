package com.iotech.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by adrien on 11/10/2015.
 */
public class Tab1Guide extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_1_disc3_guide);
        Intent myIntent = getIntent();

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(myIntent.getStringExtra("nom"));
    }
}
