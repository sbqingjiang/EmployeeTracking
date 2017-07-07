package com.tracking.employeetracking.fragments.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tracking.employeetracking.Controller;
import com.tracking.employeetracking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import info.hoang8f.widget.FButton;

/**
 * Created by Eve on 6/16/17.
 */

public class SignUp extends Fragment {

    ViewGroup root;
    FButton signupButton;
    EditText mobile, name, email;
    String nameU, emailU, mobileU, otp;

    FragmentManager fm;
    FragmentTransaction ft;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Controller cc;

    StringBuilder sb = new StringBuilder("http://rjtmobile.com/aamir/emp-mgt-sys/apps/OTP-request.php?mobile=");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.frag_signup, container, false);

        cc = (Controller) getContext().getApplicationContext();
        signupButton = (FButton) root.findViewById(R.id.signupBtn);
        name = (EditText) root.findViewById(R.id.nameInput);
        mobile = (EditText) root.findViewById(R.id.mblInput);
        email = (EditText) root.findViewById(R.id.emailInput);

        fm = getFragmentManager();
        sp = getContext().getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        editor = sp.edit();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

        return root;
    }


    public void validation() {

        nameU = name.getText().toString().trim();
        emailU = email.getText().toString().trim();
        mobileU = mobile.getText().toString().trim();

        if (mobileU.length() == 0 || emailU.length() == 0 || mobileU.length() == 0) {
            Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_LONG).show();
        } else {

            registerUser();
        }
    }


    public void registerUser() {

        sb.append(mobileU);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (!response.contains("OTP already generated")) {

                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject otpObj = jsonArray.getJSONObject(0);
                        otp = otpObj.getString("OTP");

                        saveToLocal();

                        Toast.makeText(getActivity(), "Register Succeed!", Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(), "You can login now!", Toast.LENGTH_LONG).show();
                        jumpToSignin();

                    } else {
                        Toast.makeText(getActivity(), "Mobile Number Already Registered", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG ).show();
                    // or e.printStackTrace();
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


    public void saveToLocal() {

        editor.clear();
        editor.putString("UserName", nameU);
        editor.putString("UserEmail", emailU);
        editor.putString("UserMobile", mobileU);
        editor.putString("OTP", otp);

        editor.commit();  // or apply
    }

    public void jumpToSignin() {

        SignIn signin = new SignIn();
        ft = fm.beginTransaction();
        ft.replace(R.id.login_container, signin);
        ft.addToBackStack(null);
        ft.commit();
    }

}
