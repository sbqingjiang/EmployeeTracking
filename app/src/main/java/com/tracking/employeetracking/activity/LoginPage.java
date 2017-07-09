package com.tracking.employeetracking.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.tracking.employeetracking.R;
import com.tracking.employeetracking.fragments.login.Register;
import com.tracking.employeetracking.fragments.login.SignIn;

/**
 * Created by Eve on 7/5/17.
 */

public class LoginPage extends FragmentActivity {
    private static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        if (findViewById(R.id.login_container) != null) {

            if (savedInstanceState == null) {
                Register register = new Register();
                register.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.login_container, register).commit();
//                    .add(R.id.fragment, frag_signin, "SIGN_IN").addToBackStack(null).commit();
            }
        }

    }

}
