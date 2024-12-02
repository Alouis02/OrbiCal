package com.tmsimple.orbical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import android.view.animation.LinearInterpolator;
import android.animation.ObjectAnimator;


public class MainActivity extends AppCompatActivity {

    // Declare the buttons and image view
    private Button open;
    private ImageView imageView;

    // Static HashMap to store events for each day
    public static HashMap<Integer, String> eventsMap = new HashMap<>();
    public static HashMap<Integer, String> timeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the super class's onCreate method to perform initial setup
        super.onCreate(savedInstanceState);
        // Set the content view to activity_main layout
        setContentView(R.layout.activity_main);

        // Get current date and format it
        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

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
        boolean isLeapYear = Year.now().isLeap();

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


        /////Code for the hour, minute, and second hands
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
    }
}
