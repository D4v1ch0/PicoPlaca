package com.stackbuilders.picoplaca.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.stackbuilders.picoplaca.R;
import com.stackbuilders.picoplaca.classes.Predictor;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Predictor predictor;
    private int year;
    private int month;
    private int day;
    private int dayweek;
    private int hour;
    private int minute;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        predictor = new Predictor();

        // Get current calendar date and time.
        Calendar currCalendar = Calendar.getInstance();

        year = currCalendar.get(Calendar.YEAR);
        month = currCalendar.get(Calendar.MONTH);
        day = currCalendar.get(Calendar.DAY_OF_MONTH);
        dayweek =currCalendar.get(Calendar.DAY_OF_WEEK);
        hour = currCalendar.get(Calendar.HOUR_OF_DAY);
        minute = currCalendar.get(Calendar.MINUTE);

        evaluateIfCanRoad();

        // Get date picker object.
        DatePicker datePicker = (DatePicker)findViewById(R.id.datePickerExample);
        datePicker.init(year - 1, month  + 1, day + 5, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                MainActivity.this.year = year;
                MainActivity.this.month = month;
                MainActivity.this.day = day;

                GregorianCalendar calendar = new GregorianCalendar(year, month, day-1);

                MainActivity.this.dayweek = calendar.get(calendar.DAY_OF_WEEK);

                evaluateIfCanRoad();
            }
        });

        // Get time picker object.
        TimePicker timePicker = (TimePicker)findViewById(R.id.timePickerExample);
        timePicker.setHour(this.hour);
        timePicker.setMinute(this.minute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                StringBuffer time = new StringBuffer();
                time.append(hour);
                time.append(":");
                time.append(minute);

                MainActivity.this.hour = hour;
                MainActivity.this.minute = minute;
                MainActivity.this.time = time.toString();

                evaluateIfCanRoad();
            }
        });



    }

    /* Show user selected date time in bottom text vew area. */
    private void evaluateIfCanRoad()
    {
        // Get TextView object which is used to show user pick date and time.
        TextView textView = (TextView)findViewById(R.id.textViewShowMessage);

        boolean ok = predictor.canGo(this.dayweek, this.time,"GYE-1590");

        if (ok)
        {
            textView.setText("Your vehicle is able to road!");
            textView.setTextColor(Color.GREEN);
        }
        else
        {
            textView.setText("Your vehicle is not able to road!");
            textView.setTextColor(Color.RED);
        }

        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
    }
}
