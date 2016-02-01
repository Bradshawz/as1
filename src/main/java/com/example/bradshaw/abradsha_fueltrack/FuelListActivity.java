package com.example.bradshaw.abradsha_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//Display a list of fuel logs and all users to create a new one or edit previous ones
public class FuelListActivity extends Activity {

    private FuelListSingleton singleton;
    private List<FuelLog> fuelList;
    private ArrayAdapter<FuelLog> adapter;
    private TextView totalCost;
    private ListView addedFuelLogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_list);
        addedFuelLogs = (ListView)findViewById(R.id.addedFuelLogs);
        totalCost = (TextView)findViewById(R.id.totalCost);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        singleton = FuelListSingleton.getInstance();
        singleton.initContext(this);
        fuelList = singleton.getFuelLogs();
        adapter = new ArrayAdapter<FuelLog>(this, R.layout.list_item,fuelList);
        addedFuelLogs.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalCost.setText(String.format("Overall Cost: $%.2f", singleton.getOverallCost()));
        adapter.notifyDataSetChanged();
    }

    public void addNewLog(View v)
    {
        Intent intent = new Intent(this, NewLogAcitivty.class);
        startActivity(intent);
    }

    public void itemClick(View v)
    {
        int position = addedFuelLogs.getPositionForView(v);
        Intent intent = new Intent(this, EditLogActivity.class);
        FuelLog f = (FuelLog)adapter.getItem(position);
        singleton.setAccessedLog(f);
        startActivity(intent);
    }
}
