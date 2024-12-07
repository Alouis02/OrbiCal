package com.tmsimple.orbical;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.view.animation.LinearInterpolator;
import android.animation.ObjectAnimator;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Declare the buttons and image view
    private Button open;
    private ImageView imageView;
    private DatabaseManager dbManager;

    // Static HashMap to store events for each day
    public static HashMap<Integer, String> eventsMap = new HashMap<>();
    public static HashMap<Integer, String> timeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the super class's onCreate method to perform initial setup
        super.onCreate(savedInstanceState);
        // Set the content view to activity_main layout
        setContentView(R.layout.activity_main);

        //open the database
        dbManager = DatabaseManager.getInstance(this);
        try{
            dbManager.open();
            Log.d("DatabaseDebug", "Database opened sucessfully");
        } catch (Exception e){
            Toast.makeText(this, "Error opening database: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        /*
        // Insert an example event
        dbManager.insertEvent("Meeting", "Team meeting", "2024-12-06", "10:00 AM");
        Toast.makeText(this, "Event saved!", Toast.LENGTH_LONG).show();
        */
        // Get current date and format it
        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        boolean isLeapYear = Year.now().isLeap();

        //set imageview based on isLeapYear

        if (isLeapYear) {
            setContentView(R.layout.activity_main_copy);
        } else {
            setContentView(R.layout.activity_main);
        }


        // Set the formatted date to the TextView
        TextView dateTextView = findViewById(R.id.date_text);
        dateTextView.setText(dateFormat);

        // Set image view based on the day of the week
        imageView = findViewById(R.id.monday);
        imageView = findViewById(R.id.tuesday);
        imageView = findViewById(R.id.wednesday);
        imageView = findViewById(R.id.thursday);
        imageView = findViewById(R.id.friday);
        imageView = findViewById(R.id.saturday);
        imageView = findViewById(R.id.sunday);
        Calendar cal = Calendar.getInstance();

        int currentDay = cal.get(Calendar.DAY_OF_WEEK);
        TextView eventTextView = findViewById(R.id.event_text);
        eventTextView.setText(eventsMap.getOrDefault(currentDay, "No Events"));

        TextView timeTextView = findViewById(R.id.time_text);
        timeTextView.setText(timeMap.getOrDefault(currentDay, "No Time Set"));

        // Check the day of the week and set the corresponding image resource
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            imageView.setImageResource(R.drawable.monday);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            imageView.setImageResource(R.drawable.tuesday);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            imageView.setImageResource(R.drawable.wednesday);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            imageView.setImageResource(R.drawable.thursday);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            imageView.setImageResource(R.drawable.friday);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            imageView.setImageResource(R.drawable.saturday);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            imageView.setImageResource(R.drawable.sunday);
        }

        // Code for the day marker rotation
        Calendar days = Calendar.getInstance();
        int dayOfYear = days.get(Calendar.DAY_OF_YEAR);

        // January 1st 12:00 AM
        Calendar newYear = Calendar.getInstance();
        newYear.set(Calendar.MONTH, 0);
        newYear.set(Calendar.DATE, 1);
        newYear.set(Calendar.HOUR_OF_DAY, 0);
        newYear.set(Calendar.MINUTE, 0);
        newYear.set(Calendar.SECOND, 0);

        // Check if it's the new year and set the image rotation accordingly
        if (Calendar.getInstance().compareTo(newYear) == 0) {
            imageView = findViewById(R.id.daymarker);
            imageView.animate().rotation(0).rotation(0).start();
        } else if (isLeapYear) {
            imageView = findViewById(R.id.daymarker);
            imageView.animate().rotation(0).rotation(0.9836065573770F * dayOfYear).start();
        } else {
            imageView = findViewById(R.id.daymarker);
            imageView.animate().rotation(0).rotation(0.9863013698630F * dayOfYear).start(); //rotation degree == 360/365 multiplied by the day of the year when not a leap year
        }

        //Code for the hour, minute, and second hands
        Calendar timeOfDay = Calendar.getInstance();
        int dayHour = timeOfDay.get(Calendar.HOUR_OF_DAY);
        int hourMin = timeOfDay.get(Calendar.MINUTE);
        int minSec = timeOfDay.get(Calendar.SECOND);
        int startSecDeg = minSec * 6;
        float startHourDeg = (dayHour * 15) + (hourMin * 0.25f) + 180;
        float startMinDeg = (hourMin * 6);

        imageView = findViewById(R.id.hourhand);
        imageView.animate().rotation(0).rotation((15 * dayHour) + (hourMin * 0.25f) + 180).start();
        // Create an ObjectAnimator for rotation of secondhand
        ObjectAnimator animatorHour = ObjectAnimator.ofFloat(imageView, "rotation", startHourDeg, (startHourDeg + 360f));
        animatorHour.setDuration(86400000); // 1 hour duration for a full rotation
        animatorHour.setInterpolator(new LinearInterpolator()); // Use LinearInterpolator for constant speed
        animatorHour.setRepeatCount(ObjectAnimator.INFINITE); // Keep repeating indefinitely
        animatorHour.start();

        imageView = findViewById(R.id.minutehand);
        // Create an ObjectAnimator for rotation of secondhand
        ObjectAnimator animatorMin = ObjectAnimator.ofFloat(imageView, "rotation", startMinDeg, (startMinDeg + 360f));
        animatorMin.setDuration(3600000); // 60 minute duration for a full rotation
        animatorMin.setInterpolator(new LinearInterpolator()); // Use LinearInterpolator for constant speed
        animatorMin.setRepeatCount(ObjectAnimator.INFINITE); // Keep repeating indefinitely
        animatorMin.start();

        imageView = findViewById(R.id.secondhand);
        imageView.animate().rotation(0).rotation(0).start();
        // Create an ObjectAnimator for rotation of secondhand
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", startSecDeg, (startSecDeg + 360f));
        animator.setDuration(60000); // 60 second duration for a full rotation
        animator.setInterpolator(new LinearInterpolator()); // Use LinearInterpolator for constant speed
        animator.setRepeatCount(ObjectAnimator.INFINITE); // Keep repeating indefinitely
        animator.start();

        // Initialize the button and set an OnClickListener
        open = findViewById(R.id.button1);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MainActivity2 when the button is clicked
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }//End OnCreate

    public void showEventsPopoup(View v, String dayOfWeek){
        // Inflate the popup layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_events, null);

        // Fetch events for the day of the week
        Cursor cursor = dbManager.fetchEventsByDayOfWeek(dayOfWeek);
        List<String> events = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EVENT_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EVENT_TITLE));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EVENT_TIME));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EVENT_DATE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EVENT_DESCRIPTION));
                events.add(ID + " " + title +"on " + date +" at " + time + ": "+ description);
            } while (cursor.moveToNext());
        }

        // Set up RecyclerView
        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventAdapter(events));

        // Create the PopupWindow
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        // Set a solid background for the popup
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(false); // Disable dismissal by clicking outside
        popupWindow.setFocusable(true);

        // Add a click listener to dismiss the popup when clicked
        popupView.setOnClickListener(view -> popupWindow.dismiss());

        // Show the popup at the center of the screen
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }
    //This lets the app override the default onDestroy() method to explicity close the database
    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbManager.close();
    }
    public void onViewSundayClicked(View v){
        showEventsPopoup(v,"0");
    }
    public void onViewMondayClicked(View v){
        showEventsPopoup(v, "1");
    }
    public void onViewTuesdayClicked(View v){
        showEventsPopoup(v,"2");
    }
    public void onViewWednesdayClicked(View v){
        showEventsPopoup(v, "3");
    }
    public void onViewThursdayClicked(View v){
        showEventsPopoup(v,"4");
    }
    public void onViewFridayClicked(View v){
        showEventsPopoup(v, "5");
    }
    public void onViewSaturdayClicked(View v){
        showEventsPopoup(v,"6");
    }
}