package com.iotech.discover;

/**
 * Created by adrien on 19/09/15.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

public class Tab3Favorites extends Fragment {

    String token = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_3_fav1_main, container, false);
        iniTimeSquare(view);
        return view;
    }

    public void iniTimeSquare (View view) {
       final Calendar end = Calendar.getInstance();
        end.add(Calendar.MONTH, 6);

        final CalendarPickerView calendar = (CalendarPickerView) view.findViewById(R.id.calendarViewTimeSquare);
        Date today = new Date();
        calendar.init(today, end.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE).withSelectedDate(today);
    }

}
