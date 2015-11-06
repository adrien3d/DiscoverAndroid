package com.iotech.discover;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.SimpleRecyclerAdapter;
import model.Guide;
import model.GuideData;

/**
 * Created by adrien on 10/10/2015.
 */
public class Tab1City extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    SimpleRecyclerAdapter simpleRecyclerAdapter;
    private SwipeRefreshLayout refreshLayout;
    Context context;
    String token = getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_1_disc2_city);
        Intent myIntent = getIntent();
        final String cityName = myIntent.getStringExtra("ville");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar_city);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cityName);

        ImageView header = (ImageView) findViewById(R.id.header);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lens);
        if (cityName.contains("London")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.london);
            header.setImageResource(R.drawable.london); //setBackgroundResource pour la fitter, deformation
        }
        else if (cityName.contains("Paris")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.paris);
            header.setImageResource(R.drawable.paris);
        }
        else if (cityName.contains("Lille")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lille);
            header.setImageResource(R.drawable.lille);
        }
        else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lens);
            header.setImageResource(R.drawable.lens);
        }

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                mutedColor = palette.getMutedColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.ScrollableViewGuides);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<String> listNoms = new ArrayList<String>();
<<<<<<< HEAD
        List<String> listTypes = new ArrayList
                <String>();
        int ct = 0;
        for (int i = 0; i < Guide.fake_noms.length; i++) {
            listNoms.add(Guide.fake_noms[ct]);
            listTypes.add(Guide.fake_types[ct]);
            ct++;
            if (ct == Guide.fake_noms.length) {
                ct = 0;
            }
        }

        final GuideData listeGuides=new GuideData();
        int[] availAdri = {0};
        String[] languesAdri = {"FR", "EN", "ES"};
        String[] circuitsAdri = {"À la découverte du Vieux Lille"};
        Guide adri = new Guide(0, "Lille", "Adrien Chapelet", 8 , availAdri, languesAdri, circuitsAdri, 4.5);
        listeGuides.addGuide(adri);
        int[] availMax = {0};
        String[] languesMax = {"DE"};
        String[] circuitsMax = {"Wilcomen en Billfeld"};
        Guide max = new Guide(0, "Paris", "Maxence Henneron", 8 , availAdri, languesAdri, circuitsMax, 2.5);
        listeGuides.addGuide(max);
        int[] availRom = {0};
        String[] languesRom = {"EN"};
        String[] circuitsRom = {"Welcome to Autralia"};
        Guide rom = new Guide(0, "Lens", "Romain Braems", 8 , availAdri, languesAdri, circuitsRom, 3.5);
        listeGuides.addGuide(rom);

        //String guideson = (new Gson()).toJson(listeGuides);
        //myIntent.putStringExtra("guidata", guideson);

        //String raw = myIntent.getStringExtra("guidata");
        //GuideData datas = (new Gson()).fromJson(raw, GuideData.class);
=======
        List<String> listTypes = new ArrayList<String>();
        int ct = 0;
        for (int i = 0; i < Guide.noms.length; i++) {
            listNoms.add(Guide.noms[ct]);
            listTypes.add(Guide.types[ct]);
            ct++;
            if (ct == Guide.noms.length) {
                ct = 0;
            }
        }
>>>>>>> origin/master

        if (simpleRecyclerAdapter == null) {
            simpleRecyclerAdapter = new SimpleRecyclerAdapter(listNoms, listTypes);
            recyclerView.setAdapter(simpleRecyclerAdapter);
        }

        View fab = findViewById(R.id.fabMapView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Tab1City.this, AirmapActivity.class);
                Intent intent = new Intent(Tab1City.this, CarteActivity.class);
                intent.putExtra("ville", cityName);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
