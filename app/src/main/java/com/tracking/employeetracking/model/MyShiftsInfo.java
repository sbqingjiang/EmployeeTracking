package com.tracking.employeetracking.model;

/**
 * Created by Eve on 7/3/17.
 */

public class MyShiftsInfo {

    private String shiftID;
    private String startTime;
    private String endTime;
    private String date;
    private String location;

    public MyShiftsInfo(String shiftID, String startTime, String endTime, String date, String location) {
        this.shiftID = shiftID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.location = location;
    }


    public String getShiftID() {
        return this.shiftID;
    }

    public void setShiftID(String shiftID) {
        this.shiftID = shiftID;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
