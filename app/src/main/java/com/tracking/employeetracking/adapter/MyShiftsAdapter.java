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

    ArrayList<MyShiftsInfo> mList;
    private Context mContext;
    //Controller cc;
    SharedPreferences sp;

    public MyShiftsAdapter(ArrayList<MyShiftsInfo> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_myshifts, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyShiftsAdapter.ViewHolder holder, int position) {

        //cc = (Controller) mContext.getApplicationContext();
        sp = mContext.getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        final MyShiftsInfo mInfo = mList.get(position);
        holder.myshifts_date.setText(mInfo.getDate());
        holder.myshifts_time.setText(mInfo.getStartTime() + " - " + mInfo.getEndTime());
        holder.myshifts_location.setText(sp.getString("UserName", "") + " at " + mInfo.getLocation());  // or cc
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView myshifts_date, myshifts_time, myshifts_location;
        //private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            myshifts_date = (TextView) itemView.findViewById(R.id.myshifts_date);
            myshifts_time = (TextView) itemView.findViewById(R.id.myshifts_time);
            myshifts_location = (TextView) itemView.findViewById(R.id.myshifts_location);
            //avatar = (ImageView) itemView.findViewById(R.id.avatar);

        }
    }

}
