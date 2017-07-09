package com.tracking.employeetracking.fragments.profile;

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
    Controller cc;
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user_profile, container, false);

        cc = (Controller) getContext().getApplicationContext();

        name = (EditText) view.findViewById(R.id.nameInput);
        phone = (TextView) view.findViewById(R.id.Phone);
        email = (EditText) view.findViewById(R.id.emailInput);
        logout = (FButton) view.findViewById(R.id.profile_log_out);

        name.setText(cc.getUserName());
        phone.setText(cc.getUserMobile());
        email.setText(cc.getUserEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cc.setUserName(name.getText().toString());
                cc.setUserEmail(email.getText().toString());
                name.setText(cc.getUserName());
                email.setText(cc.getUserEmail());

                Toast.makeText(getActivity(), "Your profile saved!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
