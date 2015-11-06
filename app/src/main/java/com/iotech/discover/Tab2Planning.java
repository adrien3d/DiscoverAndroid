package com.iotech.discover;

/**
 * Created by adrien on 19/09/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;

public class Tab2Planning extends Fragment implements TimeRangePickerDialog.OnTimeRangeSelectedListener {
    CalendarView calendar;
    Button selectTimeRangeButton;
    TextView timeRangeSelectedTextView;
    String token = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("token", null);
    public static final String TIMERANGEPICKER_TAG = "timerangepicker";
    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            Toast.makeText(Tab2Planning.super.getActivity(),
                    mFormatter.format(date), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDateTimeCancel()
        {
            Toast.makeText(Tab2Planning.super.getActivity(),
                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_2_plan1_main, container, false);
        initializeCalendar(view);
       View fab = view.findViewById(R.id.fabAjoutDispo);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, "DiscoDispo");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Lille");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Plage de disponibilité");
                intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");
                startActivity(intent);
            /*Pour un simple DatePicker
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");*/
/*              Pour un simple TimePicker
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");*/

            }
        });

        Button mButton = (Button) view.findViewById(R.id.debutAvail);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                new SlideDateTimePicker.Builder(getFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        .setMinDate(new Date())
                                //.setMaxDate(maxDate)
                        .setIs24HourTime(true)
                                //.setTheme(SlideDateTimePicker.HOLO_DARK)
                                //.setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectTimeRangeButton = (Button) getActivity().findViewById(R.id.bSelectTimeRangeFragment);
        timeRangeSelectedTextView = (TextView) getActivity().findViewById(R.id.tvSelectedTimeRangeFragment);
        if (savedInstanceState != null) {
            TimeRangePickerDialog tpd = (TimeRangePickerDialog) getActivity().getSupportFragmentManager()
                    .findFragmentByTag(TIMERANGEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeRangeSetListener(this);
            }
        }
        selectTimeRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimeRangePickerDialog timePickerDialog = TimeRangePickerDialog.newInstance(
                        Tab2Planning.this, false);
                timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMERANGEPICKER_TAG);
            }
        });
    }

    @Override
    public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin) {
        String startTime = startHour + " : " + startMin;
        String endTime = endHour + " : " + endMin;
        timeRangeSelectedTextView.setText(startTime + "\n" + endTime);
    }

    public void initializeCalendar(View view) {
        calendar = (CalendarView) view.findViewById(R.id.calendarView);

        calendar.setShowWeekNumber(true);
        calendar.setFirstDayOfWeek(2);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getActivity().getApplicationContext(), day + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }
}