package com.example.bradshaw.abradsha_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bradshaw on 2016-01-31.
 */
public class FuelLogTest extends ActivityInstrumentationTestCase2 {
    public FuelLogTest()
    {
        super(FuelListActivity.class);
    }

    public void testIsComplete()
    {
        FuelLog fl = new FuelLog();
        fl.setDate(new Date());
        fl.setFuelCost(10.0);
        fl.setFuelAmount(10.0);
        fl.setStation("test");
        fl.setFuelGrade("test");
        assertFalse(fl.isComplete());
        fl.setOdometerReading(20.0);
        assertTrue(fl.isComplete());
    }

    public void testSetAttributes()
    {
        FuelLog fl = new FuelLog();
        fl.setDate(new Date());
        fl.setFuelCost(10.0);
        fl.setFuelAmount(10.0);
        fl.setStation("test");
        fl.setFuelGrade("test");
        fl.setOdometerReading(20.0);
        FuelLog fl2 = new FuelLog();
        fl2.setAttributes(fl);
        assertEquals(fl2.getDate(), fl.getDate());
        assertEquals(fl2.getStation(), fl.getStation());
        assertEquals(fl2.getFuelAmount(), fl.getFuelAmount());
        assertEquals(fl2.getFuelCost(), fl.getFuelCost());
        assertEquals(fl2.getFuelGrade(), fl.getFuelGrade());
        assertEquals(fl2.getOdometerReading(), fl.getOdometerReading());
        assertEquals(fl2.getTotalCost(), fl.getTotalCost());
    }

    public void testToString()
    {
        FuelLog fl = new FuelLog();
        fl.setDate(new Date());
        fl.setFuelCost(10.0);
        fl.setFuelAmount(10.0);
        fl.setStation("test");
        fl.setFuelGrade("test");
        fl.setOdometerReading(20.0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(fl.toString(), String.format("Date: %s\nStation: %s\nTotal Cost: $%.2f",
                format.format(fl.getDate()), fl.getStation(), fl.getTotalCost()));
    }

    public void testGetTotalCost()
    {
        FuelLog fl = new FuelLog();
        fl.setFuelCost(10.0);
        fl.setFuelAmount(10.0);
        assertEquals(fl.getTotalCost(), 1.0);
    }

    public void testFuelCost()
    {
        FuelLog fl = new FuelLog();
        Double d = 10.0;
        fl.setFuelCost(d);
        assertEquals(fl.getFuelCost(), d);
    }

    public void testFuelAmount()
    {
        FuelLog fl = new FuelLog();
        Double d = 10.0;
        fl.setFuelAmount(d);
        assertEquals(fl.getFuelAmount(), d);
    }

    public void testFuelGrade()
    {
        FuelLog fl = new FuelLog();
        String s = "test";
        fl.setFuelGrade(s);
        assertEquals(fl.getFuelGrade(), s);
    }

    public void testOdometer()
    {
        FuelLog fl = new FuelLog();
        Double d = 10.0;
        fl.setOdometerReading(d);
        assertEquals(d, fl.getOdometerReading());
    }

    public void testStation()
    {
        FuelLog fl = new FuelLog();
        String s = "test";
        fl.setStation(s);
        assertEquals(fl.getStation(), s);
    }

    public void testDate()
    {
        FuelLog fl = new FuelLog();
        Date d = new Date();
        fl.setDate(d);
        assertEquals(d, fl.getDate());
    }
}
