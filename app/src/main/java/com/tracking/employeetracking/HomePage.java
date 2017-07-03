package com.tracking.employeetracking;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tracking.employeetracking.fragments.schedule.MyShiftsFragmentHost;

public class HomePage extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (findViewById(R.id.home_container)!=null) {
            if (savedInstanceState != null)
                return;

            MyShiftsFragmentHost host=new MyShiftsFragmentHost();
            getSupportFragmentManager().beginTransaction().add(R.id.home_container, host).addToBackStack(null).commit();
        }

//        toolbar= (Toolbar) findViewById(R.id.toolBar);
//        toolbar.inflateMenu(R.menu.navi_menu);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(getApplicationContext(),"TEST",Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });
//
//        setSupportActionBar(toolbar);
//        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
//        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
//


    }
}
