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

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ExerciseViewHolder>    {

    // Member variables
    private List<Exercise> mExerciseList; // cached copy of exercise list
    private LayoutInflater mInflater;
    private Context mContext;
    private String mWorkoutName;


    WorkoutListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View exercisePlaceholder = mInflater.inflate(R.layout.exerciselist_item, parent, false);
        return new ExerciseViewHolder(exercisePlaceholder, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListAdapter.ExerciseViewHolder exerciseViewHolder, int position) {
        Exercise currentExercise = mExerciseList.get(position);
        exerciseViewHolder.exerciseName.setText(currentExercise.getExerciseName()); // TODO fix all placeholders
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    void setWorkoutName(String name) {
        mWorkoutName = name;
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables
        final TextView exerciseName; // change to an edit text or whatever is needed
        final WorkoutListAdapter mAdapter;

        // Constructor
        ExerciseViewHolder(View itemView, WorkoutListAdapter adapter) {
            super(itemView);

            // Initialize CardView Textview
            exerciseName = itemView.findViewById(R.id.exercise_name_tv);
            this.mAdapter = adapter;


            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // TODO this should start the WorkoutCreatorActivity instead, needs another DayCreatorActivity before in the hierarchy
            // Get current exercise selected and start the ExerciseCreatorActivity
            Exercise currentExercise = mExerciseList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, ExerciseCreatorActivity.class);
            intent.putExtra("exercise_name", currentExercise.getExerciseName());
            intent.putExtra("description", currentExercise.getDescription());
            intent.putExtra("rest_time", currentExercise.getRestTimeMs());
            intent.putExtra("workout_name", mWorkoutName);
            mContext.startActivity(intent);
        }
    }
}
