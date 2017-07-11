package com.tracking.employeetracking.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tracking.employeetracking.R;
import com.tracking.employeetracking.model.MyShiftsInfo;

import java.util.ArrayList;

/**
 * Created by Eve on 7/3/17.
 */

public class MyShiftsAdapter extends RecyclerView.Adapter<MyShiftsAdapter.ViewHolder> {

    ArrayList<MyShiftsInfo> myShiftsInfos;
    private Context context;
    //Controller cc;
    SharedPreferences sharedPreferences;

    public MyShiftsAdapter(ArrayList<MyShiftsInfo> list, Context context) {
        this.myShiftsInfos = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myshifts, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyShiftsAdapter.ViewHolder holder, int position) {

        //cc = (Controller) mContext.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        final MyShiftsInfo mInfo = myShiftsInfos.get(position);
        holder.myshiftsDate.setText(mInfo.getDate());
        holder.myshiftsTime.setText(mInfo.getStartTime() + " - " + mInfo.getEndTime());
        holder.myshiftsLocation.setText(sharedPreferences.getString("UserName", "") + " at " + mInfo.getLocation());  // or cc
    }

    @Override
    public int getItemCount() {
        return myShiftsInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView myshiftsDate, myshiftsTime, myshiftsLocation;
        //private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            myshiftsDate = (TextView) itemView.findViewById(R.id.myshifts_date);
            myshiftsTime = (TextView) itemView.findViewById(R.id.myshifts_time);
            myshiftsLocation = (TextView) itemView.findViewById(R.id.myshifts_location);
            //avatar = (ImageView) itemView.findViewById(R.id.avatar);

        }
    }

}
