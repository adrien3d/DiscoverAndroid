package com.iotech.discover;

/**
 * Created by adrien on 19/09/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Tab1Discover extends Fragment {

    @InjectView(R.id.buttonLille)
    Button _lilleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1_disc1_main, container, false);

        ButterKnife.inject(this, rootView);

        _lilleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getActivity(), Tab1City.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}