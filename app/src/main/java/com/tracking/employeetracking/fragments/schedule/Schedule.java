package com.tracking.employeetracking.fragments.schedule;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tracking.employeetracking.Controller;
import com.tracking.employeetracking.R;
import com.tracking.employeetracking.adapter.ScheduleAdapter;
import com.tracking.employeetracking.model.ScheduleInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Eve on 7/2/17.
 */

public class Schedule extends Fragment {

    private StringBuilder stringBuilder;
    private RecyclerView recyclerView;
    ArrayList<ScheduleInfo> scheduleInfos;
    ProgressDialog progressDialog;
    Controller controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_schedule, container, false);

        controller = (Controller) getContext().getApplicationContext();
        stringBuilder = new StringBuilder("http://rjtmobile.com/aamir/emp-mgt-sys/apps/schedule.php?dateRange=July%2015-21");

        recyclerView = (RecyclerView) view.findViewById(R.id.frag_schedule_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        scheduleInfos = new ArrayList<>();


        fetchMyShifts();

        return view;
    }

    private void fetchMyShifts() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading data");

        progressDialog.show();
        StringRequest sr = new StringRequest(Request.Method.GET, stringBuilder.toString(), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                try {
                    if (!response.contains("msg")) {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Schedule");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject infoObj = jsonArray.getJSONObject(i);
                            ScheduleInfo info = new ScheduleInfo(infoObj.getString("Date"), infoObj.getString("Day"),
                                    infoObj.getString("ShiftTime"), infoObj.getString("EmployeeDesignation"), infoObj.getString("EmplpyeeThumb"));
                            scheduleInfos.add(info);
                        }

                        ScheduleAdapter adapter = new ScheduleAdapter(scheduleInfos, getContext());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(sr);
    }
}


