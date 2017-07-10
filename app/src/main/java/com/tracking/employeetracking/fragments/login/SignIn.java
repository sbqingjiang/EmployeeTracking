package com.tracking.employeetracking.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tracking.employeetracking.Controller;
import com.tracking.employeetracking.R;
import com.tracking.employeetracking.activity.HomePage;

import info.hoang8f.widget.FButton;


/**
 * Created by Eve on 6/16/17.
 */

public class SignIn extends Fragment {
    ViewGroup root;
    FragmentManager fm;
    FragmentTransaction ft;
    Controller cc;
    SharedPreferences sp;

    FButton signinBtn;
    String mobileU, otp;
    StringBuilder sb;
    TextView otpshow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.frag_signin, container, false);

        cc = (Controller) getContext().getApplicationContext();
        fm = getFragmentManager();
        sp = getContext().getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        sb = new StringBuilder("http://rjtmobile.com/aamir/emp-mgt-sys/apps/verify-OTP.php?otp=");
        otp = sp.getString("OTP", "");
        sb.append(otp);

        otpshow = (TextView) root.findViewById(R.id.otp_show);
        signinBtn = (FButton) root.findViewById(R.id.signinBtn);

        signinBtn.setText("Sign in");
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });


        otpshow.setText("Your OTP generated: " + otp);
        return root;
    }

    // get local otp, waiting for comparason
    public void fetchFromLocal() {

        mobileU = sp.getString("UserMobile", "");

        cc.setUserMobile(mobileU);
        cc.setOtp(otp);
    }

    // compare the local otp & otp from webservice to see if match
    public void loginUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("success")) {

                    fetchFromLocal();

                    Intent intent = new Intent(getActivity(), HomePage.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Welcome to Employee Tracking!", Toast.LENGTH_LONG).show();
                } else { //  not match

                    Log.i("localMobile", mobileU);
                    Log.i("OTP", otp);
                    Toast.makeText(getActivity(), "Your phone and OTP do not match", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }
}
