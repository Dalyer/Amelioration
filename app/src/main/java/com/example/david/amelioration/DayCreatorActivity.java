package com.example.david.amelioration;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Collections;
import java.util.LinkedList;


public class DayCreatorActivity extends AppCompatActivity {
    private final LinkedList<Day> mDayList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private DayListAdapter mAdapter;
    private EditText mScheduleName;
    private ScheduleViewModel mScheduleViewModel;
    private ScheduleRepository mScheduleRepository;
    private Schedule mScheduleState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_creator);
        // TODO add saveInstanceState information
        Toolbar toolbar = findViewById(R.id.toolbar_day_creator);
        setSupportActionBar(toolbar);

        // Get view elements
        mScheduleName = findViewById(R.id.schedule_name);
        // Listen for entering a new name for scheduler
        if (mScheduleName != null) {
            mScheduleName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Toast toast = Toast.makeText(DayCreatorActivity.this, "hello", Toast.LENGTH_LONG);
                    toast.show();   // remove later
                    mScheduleName.clearFocus();
                    mAdapter.setScheduleName(mScheduleName.getText().toString());
                    return false;
                }
            });
        }


        FloatingActionButton fab = findViewById(R.id.fab_day_creator);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayListSize = mDayList.size();
                // add an item to this list
                Exercise temp = new Exercise("New Exercise", "empty", 0); // TODO all names need to be unique or an id should be created as well
                LinkedList<Exercise> newList = new LinkedList<>();
                newList.addLast(temp);
                Day newDay = new Day("New Day", newList);
                mDayList.addLast(newDay);
                mRecyclerView.getAdapter().notifyItemInserted(dayListSize);
                mRecyclerView.smoothScrollToPosition(dayListSize);
                // Save schedule state here?
            }
        });

        // Create a new database entry
        // Make empty exercise object
        Exercise exerciseBase = new Exercise("New Exercise", "empty", 0);
        LinkedList<Exercise> exerciseList = new LinkedList<>();
        exerciseList.addLast(exerciseBase);
        // create base day object
        Day dayBase = new Day("New Day", exerciseList);
        mDayList.addLast(dayBase);
        // Create Schedule
        mScheduleState = new Schedule(mScheduleName.getText().toString(), mDayList); // continually add to this and save it on state save
        // Get schedule repository
        mScheduleRepository = new ScheduleRepository(getApplication());


        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view_day_creator);
        // create an adapter and supply the data to be displayed
        mAdapter = new DayListAdapter(this, mDayList, mScheduleState.getScheduleName());
        // connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // give the RecycleView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // enable swiping to remove and dragging
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mDayList, from, to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mDayList.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                // save schedule state here?
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO update layout from database
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScheduleState.updateWorkouts(mDayList);
    }

    public void saveSchedule(View view) {
        mScheduleState.updateWorkouts(mDayList);
        mScheduleRepository.insert(mScheduleState);
    }

    public String getScheduleName() {
        return mScheduleState.getScheduleName();
    }
}
