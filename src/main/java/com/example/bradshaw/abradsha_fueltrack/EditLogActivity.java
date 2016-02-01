package com.example.bradshaw.abradsha_fueltrack;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;

//Same as newlog, just pre-enter data from previous log
public class EditLogActivity extends NewLogAcitivty {

    private FuelLog editLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Pre enter previous log data
    @Override
    protected void onStart() {
        super.onStart();
        loadInfo();
    }

    private void loadInfo()
    {
        editLog = FuelListSingleton.getInstance().getAccessedLog();
        log.setAttributes(editLog);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(format.format(editLog.getDate()));
        odometer.setText(String.format("%.1f", editLog.getOdometerReading()));
        station.setText(editLog.getStation().toString());
        fuelGrade.setText(editLog.getFuelGrade().toString());
        fuelAmount.setText(String.format("%.3f", editLog.getFuelAmount()));
        fuelCost.setText(String.format("%.1f", editLog.getFuelCost()));
    }

    //Change the values in the prexisting spot in the item list
    public void saveLog(View v)
    {
        View focused = getCurrentFocus();
        if (focused != null)
        {
            focused.clearFocus();
        }
        if (log.isComplete())
        {
            editLog.setAttributes(log);
            FuelListSingleton.getInstance().saveInFile();
            finish();
        } else {
            date.setError("Please fill out all fields before saving.");
        }
    }
}
