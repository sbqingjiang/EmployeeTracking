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
    FButton logout;
    //Controller cc;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user_profile, container, false);

        //cc = (Controller) getContext().getApplicationContext();
        sp = getContext().getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        editor = sp.edit();

        name = (EditText) view.findViewById(R.id.nameInput);
        phone = (TextView) view.findViewById(R.id.Phone);
        email = (EditText) view.findViewById(R.id.emailInput);
        logout = (FButton) view.findViewById(R.id.profile_log_out);

        name.setText(sp.getString("UserName", ""));
        phone.setText(sp.getString("UserMobile", ""));
        email.setText(sp.getString("UserEmail", ""));

        // save user profile after edited
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("UserName", name.getText().toString());
                editor.putString("UserEmail", email.getText().toString());
                editor.commit();

                name.setText(sp.getString("UserName", ""));
                email.setText(sp.getString("UserEmail", ""));

                Toast.makeText(getActivity(), "Your profile saved!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
