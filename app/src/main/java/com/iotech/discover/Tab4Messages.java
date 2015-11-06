package com.iotech.discover;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adrien on 21/09/15.
 */
public class Tab4Messages extends Fragment {
    String token = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_4_mess1_main, container, false);
    }
}
