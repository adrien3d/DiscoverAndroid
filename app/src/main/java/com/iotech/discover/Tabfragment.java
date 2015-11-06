package com.iotech.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapter.TabAdapter;

/**
 * Created by adrien on 24/10/2015.
 */

public class TabFragment extends Fragment {

    private int[] tabIcons = {
            R.drawable.ic_discover_tab,
            R.drawable.ic_planning_tab,
            R.drawable.ic_favorite_tab,
            R.drawable.ic_message_tab,
            R.drawable.ic_account_tab
    };

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /*for (int i=0; i<=4; i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }*/

        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /**
         *Set an Adapter for the View Pager
         */
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager()));
        /**
         * Now , this is a workaround ,
         * The setupWithViewPager doesn't works without the runnable .
         * Maybe a Support Library Bug .
         */

        //tabLayout.getTabAt(position).setIcon(tabIcons[position]);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

}