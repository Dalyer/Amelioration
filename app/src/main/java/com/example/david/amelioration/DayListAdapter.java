package com.example.david.amelioration;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.DayViewHolder>    {

    // Member variables
    private List<Day> mDayList; //cached copy of days
    private LayoutInflater mInflater;
    private Context mContext;
    private String mScheduleName;
    private List<Schedule> mSchedules;

    DayListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View dayLayout = mInflater.inflate(R.layout.daylist_item, parent, false);
        return new DayViewHolder(dayLayout, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.DayViewHolder dayViewHolder, int position) {
        if (mDayList != null) {
            Day currentDay = mDayList.get(position);
            dayViewHolder.dayName.setText(currentDay.getDayName()); // TODO fix all placeholders
        } else {
            //covers the case of data not tbeing ready yet
            dayViewHolder.dayName.setText("No word");
        }

    }

    @Override
    public int getItemCount() {
        if (mDayList != null)
            return mDayList.size();
        else return 0;
    }

    void setDays(List<Day> days) {
        mDayList = days;
        notifyDataSetChanged();
    }

    public Day getDayAtPosition (int position) {
        return mDayList.get(position);
    }

    class DayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables
        final TextView dayName; // change to an edit text or whatever is needed
        final DayListAdapter mAdapter;

        // Constructor
        DayViewHolder(View itemView, DayListAdapter adapter) {
            super(itemView);

            // Initialize CardView Textview
            dayName = itemView.findViewById(R.id.day_name_tv);
            this.mAdapter = adapter;


            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // TODO FIX THIS TO WORK WITH VIEW MODEL
            // Get current exercise selected and start the ExerciseCreatorActivity
            Day currentDay = mDayList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, WorkoutCreatorActivity.class);
            intent.putExtra("name", currentDay.getDayName());
            intent.putExtra("scheduleName", mScheduleName);
            mContext.startActivity(intent);
        }
    }
}

