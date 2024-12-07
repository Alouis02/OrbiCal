package com.tmsimple.orbical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private static DatabaseManager instance; // Singleton instance
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    // Private constructor to prevent direct instantiation
    private DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    // Public method to get the singleton instance
    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    // Open the database connection
    public void open() throws SQLDataException {
        if (database == null || !database.isOpen()) {
            try {
                database = dbHelper.getWritableDatabase();
            } catch (Exception e) {
                throw new SQLDataException("Error opening database: " + e.getMessage());
            }
        }
    }

    // Close the database connection
    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    // Insert an event into the database
    public void insertEvent(String title, String description, String date, String time) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.EVENT_TITLE, title);
        values.put(DatabaseHelper.EVENT_DESCRIPTION, description);
        values.put(DatabaseHelper.EVENT_DATE, date);
        values.put(DatabaseHelper.EVENT_TIME, time);
        database.insert(DatabaseHelper.EVENTS_TABLE, null, values);
    }

    // Fetch events by day of the week
    public Cursor fetchEventsByDayOfWeek(String dayOfWeek) {
        String query = "SELECT * FROM " + DatabaseHelper.EVENTS_TABLE +
                " WHERE strftime('%w', " + DatabaseHelper.EVENT_DATE + ") = ?";
        return database.rawQuery(query, new String[]{dayOfWeek});
    }
}
