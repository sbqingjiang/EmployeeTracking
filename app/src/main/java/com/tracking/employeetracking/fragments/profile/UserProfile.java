package com.tracking.employeetracking.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    TextView name, phone, email;
    FButton logout;
    Controller cc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user_profile, container, false);

        cc = (Controller) getContext().getApplicationContext();

        name = (TextView) view.findViewById(R.id.Name);
        phone = (TextView) view.findViewById(R.id.Phone);
        email = (TextView) view.findViewById(R.id.Email);
        logout = (FButton) view.findViewById(R.id.profile_log_out);

        name.setText(cc.getUserName());
        phone.setText(cc.getUserMobile());
        email.setText(cc.getUserEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginPage.class);
                Toast.makeText(getActivity(), "You Are Logged Out!", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

        return view;
    }
}
