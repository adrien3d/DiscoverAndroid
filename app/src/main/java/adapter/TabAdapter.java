package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iotech.discover.Tab1Discover;
import com.iotech.discover.Tab2Planning;
import com.iotech.discover.Tab3Favorites;
import com.iotech.discover.Tab4Messages;

/**
 * Created by adrien on 24/10/2015.
 */
public class TabAdapter extends FragmentPagerAdapter  {
    public static int int_items = 4;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0 : return new Tab1Discover();
            case 1 : return new Tab2Planning();
            case 2 : return new Tab3Favorites();
            case 3 : return new Tab4Messages();
           // case 4 : return new Tab5Profile();
        }
        return null;
    }

    @Override
    public int getCount() {
        return int_items;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Disc";
            case 1 :
                return "Plan";
            case 2 :
                return "Fav";
            case 3 :
                return "Mes";
            case 5 :
                return "Pro";
        }
        return null;
    }
}
