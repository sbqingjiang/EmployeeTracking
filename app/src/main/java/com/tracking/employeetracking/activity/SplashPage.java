package com.tracking.employeetracking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tracking.employeetracking.R;

/**
 * Created by Eve on 7/5/17.
 */

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashPage.this, LoginPage.class);
                    startActivity(intent);
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
