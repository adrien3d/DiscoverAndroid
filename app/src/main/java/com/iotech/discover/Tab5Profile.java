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
public class Tab5Profile extends /*Preference*/Fragment {
    String token = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);

  /*  private ListPreference mListPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       /* mListPreference = (ListPreference)  getPreferenceManager().findPreference("preference_key");
        mListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                // insert custom code
            }
        });*/

        return inflater.inflate(R.layout.tab_5_prof1_main, container, false);
    }
}
