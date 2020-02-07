package com.example.david.amelioration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ExerciseViewHolder>    {
    private final LinkedList<Exercise> mExerciseList;
    private LayoutInflater mInflater;

    public WorkoutListAdapter(Context context, LinkedList<Exercise> exerciseList) {
        mInflater = LayoutInflater.from(context);
        this.mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View exercisePlaceholder = mInflater.inflate(R.layout.exerciselist_item, parent, false);
        return new ExerciseViewHolder(exercisePlaceholder, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListAdapter.ExerciseViewHolder exerciseViewHolder, int position) {
        Exercise mCurrent = mExerciseList.get(position);
        exerciseViewHolder.exercisePlaceholder.setText(R.string.settings_text_placeholder); // TODO fix all placeholders
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView exercisePlaceholder; // change to an edit text or whatever is needed
        final WorkoutListAdapter mAdapter;

        public ExerciseViewHolder(View itemView, WorkoutListAdapter adapter) {
            super(itemView);
            exercisePlaceholder = itemView.findViewById(R.id.exercise);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // TODO should an exercise builder here, maybe starts a new activity that gets the name etc

        }
    }
}
