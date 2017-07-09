package com.tracking.employeetracking.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tracking.employeetracking.R;

/**
 * Created by Eve on 7/5/17.
 */

public class SplashPage extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        sp = getBaseContext().getSharedPreferences("mSharedPref",Context.MODE_PRIVATE);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    if (sp.getString("OTP", "").equals("")) {
                        Intent intent = new Intent(SplashPage.this, LoginPage.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashPage.this, HomePage.class);
                        startActivity(intent);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                }
            }
        };

        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
