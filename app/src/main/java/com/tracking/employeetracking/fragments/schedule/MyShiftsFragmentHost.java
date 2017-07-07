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

public class MyShiftsFragmentHost extends Fragment{
    private FragmentTabHost mTabhost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.frag_host_myshifts, container, false);

        mTabhost = new FragmentTabHost(getContext());
        mTabhost.setup(getActivity(),getChildFragmentManager(), R.layout.frag_host_myshifts);
        mTabhost.addTab(mTabhost.newTabSpec("myshifts").setIndicator("My Shifts"), MyShifts.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("schedule").setIndicator("Schedule"), Schedule.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("openshifts").setIndicator("Open Shifts"), OpenShifts.class, null);

        for (int i = 0; i < mTabhost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) mTabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }

        //return view;
        return mTabhost;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabhost = null;
    }
}
