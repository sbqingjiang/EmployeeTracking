package com.tracking.employeetracking.fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tracking.employeetracking.Controller;
import com.tracking.employeetracking.R;
import com.tracking.employeetracking.activity.LoginPage;

import info.hoang8f.widget.FButton;

/**
 * Created by Eve on 7/7/17.
 */

public class UserProfile extends Fragment {

    TextView phone;
    EditText name, email;
    FButton saveProfile;
    //Controller cc;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user_profile, container, false);

        //cc = (Controller) getContext().getApplicationContext();
        sharedPreferences = getContext().getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        name = (EditText) view.findViewById(R.id.nameInput);
        phone = (TextView) view.findViewById(R.id.Phone);
        email = (EditText) view.findViewById(R.id.emailInput);
        saveProfile = (FButton) view.findViewById(R.id.save_profile);

        name.setText(sharedPreferences.getString("UserName", ""));
        phone.setText(sharedPreferences.getString("UserMobile", ""));
        email.setText(sharedPreferences.getString("UserEmail", ""));

        // save user profile after edited
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("UserName", name.getText().toString());
                editor.putString("UserEmail", email.getText().toString());
                editor.commit();

                name.setText(sharedPreferences.getString("UserName", ""));
                email.setText(sharedPreferences.getString("UserEmail", ""));

                Toast.makeText(getActivity(), "Your profile saved!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
