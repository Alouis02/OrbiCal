package com.tmsimple.orbical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    // Variables for Button objects
    private Button open2;
    private Button mButton;
    private EditText eventInput;
    private EditText dateInput;
    private EditText timeInput;

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

        // Declaring and initializing the event button and input fields from the layout file
        mButton = findViewById(R.id.eventbutton);
        eventInput = findViewById(R.id.event_input);
        dateInput = findViewById(R.id.date_input);
        timeInput = findViewById(R.id.time_input);

        // When Button is clicked, add event, date, and time to the selected day
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = eventInput.getText().toString();
                int dateInt = Integer.parseInt(dateInput.getText().toString());
                int timeInt = Integer.parseInt(timeInput.getText().toString());

                if (!event.isEmpty() && dateInt > 0 && timeInt >= 0) {
                    try {
                        // Set calendar with the integer date and time
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, dateInt % 100);  // date is in DD format
                        cal.set(Calendar.MONTH, (dateInt / 100) % 100 - 1);  // month is in MM format (0-based in Calendar)
                        cal.set(Calendar.YEAR, dateInt / 10000);  // year is in YYYY format
                        cal.set(Calendar.HOUR_OF_DAY, timeInt / 100);  // hour is in HH format (24-hour clock)
                        cal.set(Calendar.MINUTE, timeInt % 100);  // minute is in MM format

                        int selectedDay = cal.get(Calendar.DAY_OF_WEEK);

                        // Add event, date, and time to the static HashMap in MainActivity
                        MainActivity.eventsMap.put(selectedDay, event);
                        MainActivity.timeMap.put(selectedDay, String.format("%02d:%02d", timeInt / 100, timeInt % 100));

                        Toast.makeText(MainActivity2.this, "Event added: " + event + " on " + dateInt + " at " + timeInt, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity2.this, "Invalid date/time format.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Please enter a valid event, date, and time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
