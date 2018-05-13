package com.example.elena.testyapp.data;

import android.provider.BaseColumns;

/**
 * Created by Elena on 27/08/2017.
 */

public class HabitContract {

    public static abstract class HabitEntry implements BaseColumns {

    public static final String TABLE_NAME = "habit";

    public static final String _ID = BaseColumns._ID;

    public static final String COLUMN_HABIT_TITLE = "name";

    public static final String COLUMN_WEEKLY_HOURS = "duration";

    }

}
