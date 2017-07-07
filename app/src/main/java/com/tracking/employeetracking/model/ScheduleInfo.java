package com.tracking.employeetracking.model;

/**
 * Created by Eve on 7/6/17.
 */

public class ScheduleInfo {

    private String date;
    private String day;
    private String shiftTime;
    private String employeeDesignation;
    private String employeeThumb;

    public ScheduleInfo(String date, String day, String shiftTime, String employeeDesignation, String employeeThumb) {
        this.date = date;
        this.day = day;
        this.shiftTime = shiftTime;
        this.employeeDesignation = employeeDesignation;
        this.employeeThumb = employeeThumb;
    }

    public ScheduleInfo() {

    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getShiftTime() {
        return this.shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public String getEmployeeDesignation() {
        return this.employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeThumb() {
        return this.employeeThumb;
    }

    public void setEmployeeThumb(String employeeThumb) {
        this.employeeThumb = employeeThumb;
    }
}
