package com.iotech.discover;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.SimpleRecyclerAdapter;
import model.Guide;

/**
 * Created by adrien on 10/10/2015.
 */
public class Tab1City extends AppCompatActivity {
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_1_disc2_city);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }*/
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    SimpleRecyclerAdapter simpleRecyclerAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animate_toolbar);
        Intent myIntent = getIntent();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(myIntent.getStringExtra("ville"));

        ImageView header = (ImageView) findViewById(R.id.header);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header_lille);
/*
       if (ville.contains("lens")) {
           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header_lens);
        }*/

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                mutedColor = palette.getMutedColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<String> listNoms = new ArrayList<String>();
        int ct = 0;
        for (int i = 0; i < Guide.noms.length; i++) {
            listNoms.add(Guide.noms[ct]);
            ct++;
            if (ct == Guide.noms.length) {
                ct = 0;
            }
        }

        List<String> listTypes = new ArrayList<String>();
        ct = 0;
        for (int i = 0; i < Guide.types.length; i++) {
            listTypes.add(Guide.types[ct]);
            ct++;
            if (ct == Guide.types.length) {
                ct = 0;
            }
        }

        if (simpleRecyclerAdapter == null) {
            simpleRecyclerAdapter = new SimpleRecyclerAdapter(listNoms, listTypes);
            recyclerView.setAdapter(simpleRecyclerAdapter);
        }

        /*simpleRecyclerAdapter.SetOnItemClickListener(
        new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intentGuide = new Intent(Tab1City.this, Tab1Guide.class);
                intentGuide.putExtra("position", position);
                startActivity(intentGuide);
            }
        }
        );*/
        //simpleRecyclerAdapter.SetOnItemClickListener();
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
