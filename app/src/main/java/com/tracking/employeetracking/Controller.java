package com.tracking.employeetracking;


import android.app.Application;

/**
 * Created by Eve on 7/5/17.
 */

public class Controller extends Application {

    public static final String TAG = Controller.class.getSimpleName();

    private static Controller mInstance;


    public String userName;
    public String userMobile;
    public String userEmail;
    public String otp;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Controller getInstance() {
        return mInstance;
    }


    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}
