package com.tracking.employeetracking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tracking.employeetracking.R;
import com.tracking.employeetracking.fragments.map.Map;
import com.tracking.employeetracking.fragments.profile.UserProfile;
import com.tracking.employeetracking.fragments.schedule.MyShiftsFragmentHost;

// HomePage Activity holder of all fragments
public class HomePage extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // cc = (Controller) getApplicationContext();
        //headerUserName = (TextView) findViewById(R.id.header_username);

        initViews();
        hideScrollBar();
        setActionBar();
        setDrawerToggle();

        setListener();
        // home page container to holder all fragments
        if (findViewById(R.id.home_container) != null) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.home_container, new Map()).addToBackStack(null).commit();
            }
        }

    }
    // initialize views
    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
   //setActionBar
    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void hideScrollBar() {
        mNavigationView.getChildAt(0).setVerticalScrollBarEnabled(false);
    }

    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    // set navigation drawer items click listener
    private void setListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.schedule:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new MyShiftsFragmentHost()).addToBackStack(null).commit();
                        break;
                    case R.id.dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new Map()).addToBackStack(null).commit();
                        break;
                    case R.id.logout:
                        Intent it = new Intent(HomePage.this, LoginPage.class);
                        startActivity(it);
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new UserProfile()).addToBackStack(null).commit();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


}
