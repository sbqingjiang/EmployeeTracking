package com.tracking.employeetracking.fragments.map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tracking.employeetracking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.tracking.employeetracking.R.id.my_map;
import static com.tracking.employeetracking.R.layout.dialog;

/**
 * Created by yinqingjiang on 7/5/17.
 */
// main fragment of homepage shows office location and clock in
public class Map extends Fragment implements OnMapReadyCallback {
    private TextView tv_shift;
    private TextView tv_date;
    private String startTime;
    private String endTime;
    private String dateTime;
    private String url = "http://rjtmobile.com/aamir/emp-mgt-sys/apps/shift_info.php?mobile=55565454";
    private StringBuilder sb = new StringBuilder("http://rjtmobile.com/aamir/emp-mgt-sys/apps/clock_in.php? mobile= 55565454&notes=");
    private String url_shift = "http://rjtmobile.com/aamir/emp-mgt-sys/apps/emp_in_shift.php?shiftID=1";

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

        LinearLayout linear_email = (LinearLayout) view.findViewById(R.id.linear_emial);
        LinearLayout liear = (LinearLayout) view.findViewById(R.id.linearLayout);
        liear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //showEmailDialog();
                showDialog();
                //sendGmail(getActivity(),"test","test");

            }
        });
        linear_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEmailDialog();
            }
        });
        return view;
    }

    // create google map
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng sydney = new LatLng(41.9797770, -88.5337430);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Office")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
//      googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        Circle circle = googleMap.addCircle(new CircleOptions()
                .center(sydney)
                .radius(1000)
                .strokeColor(Color.BLUE)
                .fillColor(Color.parseColor("#BBDEFB")));

        CameraUpdate center =
                CameraUpdateFactory.newLatLng(sydney);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }

    // get next time shift information from url
    public void requestInfor() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Shift Information");
                    JSONObject obj = array.getJSONObject(0);
                    startTime = obj.getString("ShiftStartTime");
                    tv_shift.setText(startTime.substring(0, 5));

                    endTime = obj.getString("ShiftEndTime");
                    // Toast.makeText(getContext(), endTime, Toast.LENGTH_SHORT).show();
                    tv_shift.append("p-");
                    tv_shift.append(endTime.substring(0, 5));
                    tv_shift.append("p");

                    dateTime = obj.getString("ShiftDate");
                    tv_date.setText(dateTime);
                    // Toast.makeText(getContext(), startTime, Toast.LENGTH_LONG).show();
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

    // show alert dialog
    public void showDialog() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View customTitleView = inflater.inflate(R.layout.dialog_title, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setCustomTitle(customTitleView);

        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(dialog, null);
        builder.setView(dialogView);
        final EditText editText = (EditText) dialogView.findViewById(R.id.editText);
        Button cancel = (Button) dialogView.findViewById(R.id.cancel);
        Button clockin = (Button) dialogView.findViewById(R.id.checkin);
        final AlertDialog dialog = builder.create();


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        clockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb.append(editText.getText().toString().trim());
                submit(sb.toString());
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    // submit clock in note to server
    public void submit(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("updated")) {
                    Toast.makeText(getActivity(), "Attendance note updated", Toast.LENGTH_LONG).show();
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

    // send gmail
    public void sendGmail(Activity activity, String subject, String text) {
        Intent gmailIntent = new Intent();
        gmailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        gmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        gmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        try {
            activity.startActivity(gmailIntent);
        } catch (ActivityNotFoundException ex) {
            // handle error
        }
    }

    public void showEmailDialog() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View customTitleView = inflater.inflate(R.layout.email_title, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setCustomTitle(customTitleView);

        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_email, null);
        builder.setView(dialogView);
        final EditText title = (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText content = (EditText) dialogView.findViewById(R.id.editText);
        Button cancel = (Button) dialogView.findViewById(R.id.cancel_email);
        Button send = (Button) dialogView.findViewById(R.id.send_email);
        final AlertDialog dialog = builder.create();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendGmail(getActivity(), title.getText().toString(), content.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
