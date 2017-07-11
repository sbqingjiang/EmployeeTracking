package com.tracking.employeetracking.fragments.schedule;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tracking.employeetracking.R;


/**
 * Created by Eve on 7/3/17.
 */

public class MyShiftsFragmentHost extends Fragment {
    private FragmentTabHost fragmentTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.frag_host_myshifts, container, false);

        fragmentTabHost = new FragmentTabHost(getContext());
        fragmentTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.frag_host_myshifts);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("myshifts").setIndicator("My Shifts"), MyShifts.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("schedule").setIndicator("Schedule"), Schedule.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("openshifts").setIndicator("Open Shifts"), OpenShifts.class, null);

        for (int i = 0; i < fragmentTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) fragmentTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }

        //return view;
        return fragmentTabHost;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTabHost = null;
    }
}
