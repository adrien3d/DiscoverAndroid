package com.iotech.discover;

/**
 * Created by adrien on 19/09/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab1Discover tab1 = new Tab1Discover();
                return tab1;
            case 1:
                Tab2Planning tab2 = new Tab2Planning();
                return tab2;
            case 2:
                Tab3Favorites tab3 = new Tab3Favorites();
                return tab3;
            case 3:
                Tab4Messages tab4 = new Tab4Messages();
                return tab4;
            case 4 :
                Tab5Profile tab5 = new Tab5Profile();
                return tab5;/*
                return new PreferenceFragment() {
                    @Override
                    public void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        addPreferencesFromResource(R.xml.settings);
                    }
                };*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}