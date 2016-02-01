package com.example.bradshaw.abradsha_fueltrack;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//Used to create new fuel logs
public class NewLogAcitivty extends Activity {

    protected FuelLog log;
    protected EditText date;
    protected EditText odometer;
    protected EditText station;
    protected EditText fuelGrade;
    protected EditText fuelAmount;
    protected EditText fuelCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log_acitivty);
        date = (EditText)findViewById(R.id.date);
        odometer = (EditText)findViewById(R.id.odometer);
        station = (EditText)findViewById(R.id.station);
        fuelGrade = (EditText)findViewById(R.id.fuelGrade);
        fuelAmount = (EditText)findViewById(R.id.fuelAmount);
        fuelCost = (EditText)findViewById(R.id.fuelTotal);
    }

    //Put focus changed listeners on text fields, and set the fuel log to value when focus changes
    @Override
    protected void onStart()
    {
        super.onStart();
        log = new FuelLog();
        odometer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    try {
                        log.setOdometerReading(Double.parseDouble(odometer.getText().toString()));
                    } catch (Exception e)
                    {
                        odometer.setError("Fill in with a number.");
                    }
                }
            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    try {
                        log.setDate(df.parse(date.getText().toString()));
                    } catch (ParseException e) {
                        date.setError("Make sure the date is in yyyy-MM-dd format.");
                    }
                }
            }
        });
        station.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    log.setStation(station.getText().toString());
                }
            }
        });
        fuelGrade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    log.setFuelGrade(fuelGrade.getText().toString());
                }
            }
        });
        fuelAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    try {
                        log.setFuelAmount(Double.parseDouble(fuelAmount.getText().toString()));
                    } catch (Exception e)
                    {
                        fuelAmount.setError("Fill in with a number.");
                    }
                }
            }
        });
        fuelCost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    try {
                        log.setFuelCost(Double.parseDouble(fuelCost.getText().toString()));
                    } catch (Exception e)
                    {
                        fuelCost.setError("Fill in with a number.");
                    }
                }
            }
        });
    }

    //Unselect all focuses and make sure log has all needed info
    public void saveLog(View v)
    {
        View focused = getCurrentFocus();
        if (focused != null)
        {
            focused.clearFocus();
        }
        if (log.isComplete())
        {
            FuelListSingleton.getInstance().addLog(log);
            finish();
        }
        else {
            date.setError("Please fill out all fields before saving.");
        }
    }

    //Don't do anything and return
    public void backClick(View v)
    {
        finish();
    }
}
