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
import android.widget.Button;
import android.widget.EditText;
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

import at.markushi.ui.CircleButton;
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

    FButton signinBtn, tryRegisterBtn;
    String nameU, mobileU, emailU, otp;
    StringBuilder sb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.signin, container, false);

        cc = (Controller) getContext().getApplicationContext();
        fm = getFragmentManager();
        sp = getContext().getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        sb = new StringBuilder("http://rjtmobile.com/aamir/emp-mgt-sys/apps/verify-OTP.php?otp=");
        otp = sp.getString("OTP", "");
        sb.append(otp);

        signinBtn = (FButton) root.findViewById(R.id.signinBtn);
        tryRegisterBtn = (FButton) root.findViewById(R.id.tryRegister);

        signinBtn.setText("Sign in as " + sp.getString("UserName", ""));
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });


        tryRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUp signup = new SignUp();
                ft = fm.beginTransaction();
                ft.replace(R.id.login_container, signup).addToBackStack(null);
                ft.commit();
            }
        });

        return root;
    }


    public void fetchFromLocal() {

        nameU = sp.getString("UserName", "");
        emailU = sp.getString("UserEmail", "");
        mobileU = sp.getString("UserMobile", "");

        cc.setUserName(nameU);
        cc.setUserMobile(mobileU);
        cc.setUserEmail(emailU);
        cc.setOtp(otp);
    }


    public void loginUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("success")){

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
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG ).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }
}
