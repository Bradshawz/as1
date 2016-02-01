package com.example.bradshaw.abradsha_fueltrack;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bradshaw on 2016-01-30.
 */

//Used to transfer data between activities, allows access to the fuellog list and loads/saves it
public class FuelListSingleton {
    private static FuelListSingleton ourInstance = new FuelListSingleton();
    private static final String FILENAME = "file.sav";
    private ArrayList<FuelLog> fuelLogs;
    private Context context;
    private FuelLog recentLog;

    //Give a list that cant be changed so they have to go through accessors
    public List<FuelLog> getFuelLogs() {
        return Collections.unmodifiableList(fuelLogs);
    }

    public static FuelListSingleton getInstance() {
        return ourInstance;
    }

    private FuelListSingleton() {
    }

    public void initContext(Context context)
    {
        this.context = context;
        loadFromFile();
    }

    public void addLog(FuelLog log)
    {
        fuelLogs.add(log);
        saveInFile();
    }


    private void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<FuelLog>>() {}.getType();
            fuelLogs = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fuelLogs  = new ArrayList<FuelLog>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Return the sum of total costs in the list
    public Double getOverallCost()
    {
        if (fuelLogs.size() == 0)
        {
            return 0.0;
        }
        Double total = 0.0;
        for(FuelLog f : fuelLogs)
        {
            total += f.getTotalCost();
        }
        return total;
    }

    public void setAccessedLog(FuelLog log)
    {
        this.recentLog = log;
    }

    public FuelLog getAccessedLog() { return this.recentLog; }

    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(fuelLogs, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
