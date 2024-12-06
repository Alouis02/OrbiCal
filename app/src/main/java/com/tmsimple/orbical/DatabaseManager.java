package com.tmsimple.orbical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insertEvent(String title, String description, String date, String time) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.EVENT_TITLE, title);
        values.put(DatabaseHelper.EVENT_DESCRIPTION, description);
        values.put(DatabaseHelper.EVENT_DATE, date);
        values.put(DatabaseHelper.EVENT_TIME, time);
        database.insert(DatabaseHelper.EVENTS_TABLE, null, values);
    }

    // Fetch Events by Day of Week
    // by using %w we can reorganize the Event_date columm of the table to reorginized as integers from 0-6 where o is events on sunday, 1 is monday, etc.
    public Cursor fetchEventsByDayOfWeek(String dayOfWeek) {
        String query = "SELECT * FROM " + DatabaseHelper.EVENTS_TABLE +
                " WHERE strftime('%w', " + DatabaseHelper.EVENT_DATE + ") = ?";
        return database.rawQuery(query, new String[]{dayOfWeek});
    }
}
