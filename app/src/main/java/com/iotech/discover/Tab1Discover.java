package com.iotech.discover;

/**
 * Created by adrien on 19/09/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapter.TravelListAdapter;

public class Tab1Discover extends Fragment {

    /*@InjectView(R.id.buttonLille)
    Button _lilleButton;*/
    String token = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TravelListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1_disc1_main, container, false);
       // ButterKnife.inject(this, rootView);

        //mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        final FragmentActivity c = getActivity();
        LinearLayoutManager mStaggeredLayoutManager = new LinearLayoutManager(c);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.listCities);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setHasFixedSize(true); //Data size is fixed - improves performance

        String token = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);
        mAdapter = new TravelListAdapter(getContext());

        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }


    TravelListAdapter.OnItemClickListener onItemClickListener = new TravelListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            String[] placeNameArray = {"London", "Paris", "Lille", "Lens"};
            Intent transitionIntent = new Intent(getActivity(), Tab1City.class);
            transitionIntent.putExtra("ville", placeNameArray[position]);
            startActivity(transitionIntent);
        }
    };
}