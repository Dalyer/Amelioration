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
    private LinkedList<Day> mDayList; //cached copy of days
    private LayoutInflater mInflater;
    private Context mContext;

    DayListAdapter(Context context, LinkedList<Day> dayList) {
        mInflater = LayoutInflater.from(context);
        this.mDayList = dayList;
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
        Day currentDay = mDayList.get(position);
        dayViewHolder.dayName.setText(currentDay.getName()); // TODO fix all placeholders
    }

    @Override
    public int getItemCount() {
        return mDayList.size();
    }

    void setDays(LinkedList<Day> days) {
        mDayList = days;
        notifyDataSetChanged();
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
            // TODO this should start the WorkoutCreatorActivity instead, needs another DayCreatorActivity before in the hierarchy
            // Get current exercise selected and start the ExerciseCreatorActivity
            Day currentDay = mDayList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, WorkoutCreatorActivity.class);
            intent.putExtra("name", currentDay.getName());
           // intent.putExtra("Exercises", currentDay.getExercises()); //TODO this shit ain't gonna work
            mContext.startActivity(intent);
        }
    }
}

