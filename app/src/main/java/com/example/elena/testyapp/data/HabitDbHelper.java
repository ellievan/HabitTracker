package com.example.elena.testyapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elena.testyapp.data.HabitContract.HabitEntry;
import static android.content.ContentValues.TAG;

/**
 * Created by Elena on 27/08/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {



    private HabitDbHelper mDbHelper;
    private static final String DATABASE_NAME = "allmyhabits.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "(" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitEntry.COLUMN_HABIT_TITLE + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_WEEKLY_HOURS + " TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_HABIT_TABLE);
        Log.d("CHECK", SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
    public void insertHabit (String title, int weeklyHours) {
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE, title);
        values.put(HabitEntry.COLUMN_WEEKLY_HOURS, weeklyHours);

        SQLiteDatabase writableDb = getWritableDatabase();

        long newRowId = writableDb.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        if (newRowId != -1) {
            Log.d(TAG, "Habit saved with row ID: " + newRowId);
        }else{
            Log.d(TAG, "Error with saving habit");
        }
    }

    public Cursor readHabit() {
        SQLiteDatabase readableDb = getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_TITLE,
                HabitEntry.COLUMN_WEEKLY_HOURS
        };

        Cursor cursor = readableDb.query(HabitEntry.TABLE_NAME, projection, null, null, null,null, null);
        return cursor;
    }


}
