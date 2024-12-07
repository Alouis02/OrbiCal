package com.tmsimple.orbical;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private Button open2;
    private Button eventButton;
    private EditText eventInput;
    private EditText dateInput;
    private EditText timeInput;
    private EditText descriptionInput;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Access the singleton DatabaseManager instance
        dbManager = DatabaseManager.getInstance(this);
        try {
            dbManager.open();
        } catch (Exception e) {
            Toast.makeText(this, "Error opening database: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView dateTextView = findViewById(R.id.date_text2);
        dateTextView.setText(dateFormat);

        open2 = findViewById(R.id.button2);
        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        eventButton = findViewById(R.id.eventbutton);
        eventInput = findViewById(R.id.event_input);
        dateInput = findViewById(R.id.date_input);
        timeInput = findViewById(R.id.time_input);
        descriptionInput = findViewById(R.id.description_input);
        /*
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = eventInput.getText().toString();
                String date = dateInput.getText().toString();
                String time = timeInput.getText().toString();

                if (!event.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                    String eventDetails = "Event: " + event + "\nDate: " + date + "\nTime: " + time;
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    intent.putExtra("eventDetails", eventDetails);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Please enter event, date, and time", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    } // This ends the onCreate method

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close(); // Close the database when the activity is destroyed
    }
    public void btnInsertEventPressed(View v) {
        String title = eventInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String date = dateInput.getText().toString();
        String time = timeInput.getText().toString();

        dbManager.insertEvent(title, description, date, time);
    }
}