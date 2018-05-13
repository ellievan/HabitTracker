package com.example.elena.testyapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.elena.testyapp.data.HabitContract.HabitEntry;
import com.example.elena.testyapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;
    private static final String TAG = HabitDbHelper.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        mDbHelper.insertHabit("studying", 10);
        mDbHelper.insertHabit("running", 6);

        Cursor cursor = mDbHelper.readHabit();
        try {
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TITLE);
            int weeklyHoursColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_WEEKLY_HOURS);

            Log.d(TAG, "ID | TITLE | HOURS");
            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                String title = cursor.getString(titleColumnIndex);
                int weeklyHours = cursor.getInt(weeklyHoursColumnIndex);

                Log.d(TAG, id + " " + title + " " + weeklyHours);
            }
        } finally {
            cursor.close();
        }
    }
}
