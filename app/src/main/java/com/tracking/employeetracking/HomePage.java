package com.tracking.employeetracking;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tracking.employeetracking.fragments.schedule.MyShiftsFragmentHost;

public class HomePage extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initViews();
        setActionBar();
        setDrawerToggle();

        if (findViewById(R.id.home_container)!=null) {
            if (savedInstanceState != null)
                return;

            MyShiftsFragmentHost host=new MyShiftsFragmentHost();
            getSupportFragmentManager().beginTransaction().add(R.id.home_container, host).addToBackStack(null).commit();
        }

    }
    private void initViews()
    {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
    }
    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        /*同步drawerlayout的状态*/
        toggle.syncState();
    }


}
