package com.tmsimple.orbical;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper{
    static final  String DATABASE_NAME = "CALENDAR_APP.DB";
    static final int DATABASE_VERSION = 2;

    // Events Table, recording table name, ID as if it were a primary key, the title, description, date, and time
    static final String EVENTS_TABLE = "EVENTS";
    static final String EVENT_ID = "_ID";
    static final String EVENT_TITLE = "title";
    static final String EVENT_DESCRIPTION = "description";
    static final String EVENT_DATE = "date";
    static final String EVENT_TIME = "time";

    private static final String CREATE_EVENTS_TABLE_QUERY =
            "CREATE TABLE " + EVENTS_TABLE + " (" +
                    EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EVENT_TITLE + " TEXT NOT NULL, " +
                    EVENT_DESCRIPTION + " TEXT, " +
                    EVENT_DATE + " TEXT NOT NULL, " +
                    EVENT_TIME + " TEXT NOT NULL);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
        onCreate(db);
    }
}
