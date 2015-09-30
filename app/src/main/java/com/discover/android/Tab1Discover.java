package com.discover.android;

/**
 * Created by adrien on 19/09/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1Discover extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*ArrayList<String> villes = new ArrayList<>("Lens", "Lille", "Paris");
        //String[] villes = {"Lens", "Lille", "Paris"};

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, villes);

        listView.setAdapter(itemsAdapter);*/
        return inflater.inflate(R.layout.tab_1_fragment, container, false);
    }
}