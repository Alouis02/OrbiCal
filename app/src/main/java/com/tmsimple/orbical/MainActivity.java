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
// Additional imports for popup window
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

// Main class for the activity
public class MainActivity extends AppCompatActivity {

    // Declare the buttons and image view
    private Button open;
    private Button showPopupButton;
    private ImageView imageView;

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
            //rotation degree == 360/365 multiplied by the day of the year when not a leap year
            imageView.animate().rotation(0).rotation(0.9863013698630F * dayOfYear).start();
        }

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

        // Initialize the popup button and set an OnClickListener
        //showPopupButton = findViewById(R.id.button_show_popup);
        showPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Programmatically calling the popup method
                onButtonShowPopupWindowClick(v); // Passing the current view (v)
            }
        });
    }

    // Method to show the popup window
    public void onButtonShowPopupWindowClick(View view) {
        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // Create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // Show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // Dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
