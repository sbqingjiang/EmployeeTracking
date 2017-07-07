package com.tracking.employeetracking.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tracking.employeetracking.Controller;
import com.tracking.employeetracking.R;

/**
 * Created by Eve on 7/7/17.
 */

public class UserProfile extends Fragment {

    TextView name, phone, email;
    Controller cc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user_profile, container, false);

        cc = (Controller) getContext().getApplicationContext();

        name = (TextView) view.findViewById(R.id.Name);
        phone = (TextView) view.findViewById(R.id.Phone);
        email = (TextView) view.findViewById(R.id.Email);

        name.setText(cc.getUserName());
        phone.setText(cc.getUserMobile());
        email.setText(cc.getUserEmail());

        return view;
    }
}
