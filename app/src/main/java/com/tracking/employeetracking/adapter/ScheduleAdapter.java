package com.tracking.employeetracking.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tracking.employeetracking.R;
import com.tracking.employeetracking.model.ScheduleInfo;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Eve on 7/6/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{

    ArrayList<ScheduleInfo> mList;
    private Context mContext;
    //Controller cc;
    SharedPreferences sp;

    public ScheduleAdapter(ArrayList<ScheduleInfo> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_schedule, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {

        //cc = (Controller) mContext.getApplicationContext();
        sp = mContext.getSharedPreferences("mSharedPref", Context.MODE_PRIVATE);
        final ScheduleInfo mInfo = mList.get(position);
        holder.schedule_date.setText(mInfo.getDay() + ", " + mInfo.getDate());
        holder.schedule_time.setText(mInfo.getShiftTime());
        holder.schedule_designation.setText(mInfo.getEmployeeDesignation());

        Picasso.with(mContext).load(mInfo.getEmployeeThumb()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView schedule_date, schedule_time, schedule_designation;
        private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            schedule_date = (TextView) itemView.findViewById(R.id.schedule_date);
            schedule_time = (TextView) itemView.findViewById(R.id.schedule_time);
            schedule_designation = (TextView) itemView.findViewById(R.id.schedule_designation);
            avatar = (ImageView) itemView.findViewById(R.id.schedule_avatar);

        }
    }

}
