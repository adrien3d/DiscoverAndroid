package com.iotech.discover;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
<<<<<<< HEAD
import android.support.v7.app.AlertDialog;
=======
>>>>>>> origin/master
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by adrien on 11/10/2015.
 */
public class Tab1Guide extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbar;
    String token = getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_1_disc3_guide);
        Intent myIntent = getIntent();

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
<<<<<<< HEAD

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar_guide);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(myIntent.getStringExtra("nom"));

        View fab = findViewById(R.id.fabReserver);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Snackbar.make(v, "Guide réservé", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();*/
                AlertDialog.Builder builder = new AlertDialog.Builder(Tab1Guide.this);
                builder.setTitle("Choix du créneau")
                        .setMultiChoiceItems(R.array.availGuide, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                        // Your method here
                                    }
                                });
                builder.setPositiveButton("OK", null).setNegativeButton("Cancel",null).show();

            }
        });
=======

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(myIntent.getStringExtra("nom"));
>>>>>>> origin/master
    }
}
