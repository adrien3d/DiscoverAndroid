package com.iotech.discover;

/**
 * Created by adrien on 19/09/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iotech.discover.R;

public class Tab1Discover extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ArrayList<String> villes = new ArrayList<>("Lens", "Lille", "Paris");
/*        String[] villes = {"Lens", "Lille", "Paris"};

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.tab_1_disc1_main, villes);

        ListView lv = (ListView) lv.findViewById(R.id.listView);
        lv.setAdapter(itemsAdapter);*/

        return inflater.inflate(R.layout.tab_1_disc1_main, container, false);
    }
}