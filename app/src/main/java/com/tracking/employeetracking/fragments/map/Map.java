package com.tracking.employeetracking.fragments.map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tracking.employeetracking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.tracking.employeetracking.R.id.my_map;

/**
 * Created by yinqingjiang on 7/5/17.
 */

public class Map extends Fragment implements OnMapReadyCallback {
    private TextView tv_shift;
    private TextView tv_date;
    private String startTime;
    private String endTime;
    private String dateTime;
    private String url = "http://rjtmobile.com/aamir/emp-mgt-sys/apps/shift_info.php?mobile=55565454";
    private StringBuilder sb = new StringBuilder("http://rjtmobile.com/aamir/emp-mgt-sys/apps/clock_in.php? mobile= 55565454&notes=sorry I m late I missed my bus");
    private String url_shift="http://rjtmobile.com/aamir/emp-mgt-sys/apps/emp_in_shift.php?shiftID=1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_map, container, false);
        tv_shift = (TextView) view.findViewById(R.id.next_shift);
        tv_date = (TextView) view.findViewById(R.id.shift_date);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(my_map);
        mapFragment.getMapAsync(this);
        requestInfor();


        LinearLayout liear = (LinearLayout) view.findViewById(R.id.linearLayout);
        liear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng sydney = new LatLng(41.9797770, -88.5337430);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Office"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        CameraUpdate center =
                CameraUpdateFactory.newLatLng(sydney);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }

    public void requestInfor() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Shift Information");
                    JSONObject obj = array.getJSONObject(0);
                    startTime = obj.getString("ShiftStartTime");
                    tv_shift.setText(startTime.substring(0,5));

                    endTime = obj.getString("ShiftEndTime");
                    Toast.makeText(getContext(), endTime, Toast.LENGTH_SHORT).show();
                    tv_shift.append("p-");
                    tv_shift.append(endTime.substring(0,5));
                    tv_shift.append("p");

                    dateTime = obj.getString("ShiftDate");
                    tv_date.setText(dateTime);
                    Toast.makeText(getContext(), startTime, Toast.LENGTH_LONG).show();
                    ;
                    //endTime = info.getString("ShiftEndTime");
                    //date = info.getString("ShiftDate");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

    public void clockIn() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("updated")) {

                    Toast.makeText(getActivity(), "Welcome to Employee Tracking!", Toast.LENGTH_LONG).show();
                } else { //  not match

                    Toast.makeText(getActivity(), "Your phone and OTP do not match", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

    public void showDialog() {
        final AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
        dialog.setTitle("Would you like to clock in ? ");
        dialog.setMessage("2:00 -800");
        dialog.setPositiveButton("CLOCK IN",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        dialog.show();

    }

}
