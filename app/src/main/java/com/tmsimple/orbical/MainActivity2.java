package com.tmsimple.orbical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;
// Additional imports for Add Event
import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    // Variables for Button objects
    private Button open2;
    private Button mButton;
    private Date mStartTime;
    private Date mEndTime;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Calls the super class's onCreate method to perform initial setup
        super.onCreate(savedInstanceState);
        // Sets the content view to activity_main2 layout
        setContentView(R.layout.activity_main2);

        // Creates a Calendar instance and formats the current date
        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        // Finds the TextView by its ID and sets the formatted date
        TextView dateTextView = findViewById(R.id.date_text2);
        dateTextView.setText(dateFormat);

        // Finds the Button by its ID and sets an OnClickListener to start MainActivity
        open2 = findViewById(R.id.button2);
        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Declaring and initializing the event button from the layout file
        mButton = findViewById(R.id.eventbutton);

        // Event start and end time with date
        String startTime = "2022-02-1T09:00:00";
        String endTime = "2022-02-1T12:00:00";

        // Parsing the date and time
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            mStartTime = mSimpleDateFormat.parse(startTime);
            mEndTime = mSimpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // When Button is clicked, Intent is started to create an event with the given time
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartTime != null && mEndTime != null) {
                    Intent mIntent = new Intent(Intent.ACTION_EDIT);
                    mIntent.setType("vnd.android.cursor.item/event");
                    mIntent.putExtra("beginTime", mStartTime.getTime());
                    mIntent.putExtra("time", true);
                    mIntent.putExtra("rule", "FREQ=YEARLY");
                    mIntent.putExtra("endTime", mEndTime.getTime());
                    mIntent.putExtra("title", "Geeksforgeeks Event");
                    startActivity(mIntent);
                } else {
                    // Handle the case where mStartTime or mEndTime is null
                    Toast.makeText(MainActivity2.this, "Invalid date/time format.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}