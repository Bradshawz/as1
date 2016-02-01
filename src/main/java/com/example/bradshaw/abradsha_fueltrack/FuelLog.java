package com.example.bradshaw.abradsha_fueltrack;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by Bradshaw on 2016-01-30.
 */

//Basic data included in fuel log
public class FuelLog {
    private Date date;
    private String station;
    private Double odometerReading;
    private String fuelGrade;
    private Double fuelAmount;
    private Double fuelCost;
    private Double totalCost;
    private Double centsPerDollar = 100.0;

    public Date getDate() {
        return date;
    }

    public FuelLog() {
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Double getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(Double odometerReading) {
        this.odometerReading = odometerReading;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public Double getFuelAmount() {
        return fuelAmount;
    }

    //Calculate total cost on set also
    public void setFuelAmount(Double fuelAmount) {
        this.fuelAmount = fuelAmount;
        if (fuelCost != null && fuelAmount != null)
        {
            totalCost = fuelAmount * fuelCost / centsPerDollar;
        }
    }

    public Double getFuelCost() {
        return fuelCost;
    }

    //Calculate total cost on set also
    public void setFuelCost(Double fuelCost) {
        this.fuelCost = fuelCost;
        double centsPerDollar = 100;
        if (fuelAmount != null && fuelCost != null)
        {
            totalCost = fuelAmount * fuelCost / centsPerDollar;
        }
    }

    public Double getTotalCost() {
        return totalCost;
    }

    //Used to display in list items
    @Override
    public String toString()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("Date: %s\nStation: %s\nTotal Cost: $%.2f",
                format.format(date), station, totalCost);
    }

    //Check to make sure no fields are null
    public Boolean isComplete()
    {
        return date != null && station != null && odometerReading != null && fuelGrade != null
                && fuelAmount != null && fuelCost != null && totalCost != null;
    }

    //Clone the objects attributes from another object
    public void setAttributes(FuelLog log)
    {
        this.date = log.getDate();
        this.station = log.getStation();
        this.odometerReading = log.getOdometerReading();
        this.fuelGrade = log.getFuelGrade();
        this.fuelAmount = log.getFuelAmount();
        this.fuelCost = log.getFuelCost();
        this.totalCost = log.getTotalCost();
    }
}
