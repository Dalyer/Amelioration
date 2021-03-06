package com.example.david.amelioration;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private ScheduleRepository mScheduleRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mScheduleRepository = new ScheduleRepository(getApplication(), 0 ,0, 0); // Id's don't matter because its clearing all
    }

    public void clearDatabase(View view) {
        AlertDialog.Builder clearDatabaseAlert = new AlertDialog.Builder(SettingsActivity.this);

        clearDatabaseAlert.setTitle("Warning");
        clearDatabaseAlert.setMessage("Are you sure you want to clear the database?");

        // dialog buttons
        clearDatabaseAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mScheduleRepository.deleteAll();
                Toast toast = Toast.makeText(SettingsActivity.this, "Database Cleared", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        clearDatabaseAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        // Create and show alert
        clearDatabaseAlert.show();

    }

}
