package com.tmsimple.orbical;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button open2;

    private EditText eventInput;
    private Spinner IDInput;
    private Spinner dateInput;
    private Spinner timeInput;


    private EditText descriptionInput;
    private DatabaseManager dbManager;
    String selectedDate, selectedTime, selectedID;

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


        eventInput = findViewById(R.id.event_input);
        dateInput = findViewById(R.id.date_input);
        IDInput = findViewById(R.id.ID_input);
        timeInput = findViewById(R.id.time_input);
        descriptionInput = findViewById(R.id.description_input);

        //fill the ID dropdown with possible IDs
        List<String> eventIDs= Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17");
        ArrayAdapter<String> IDAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventIDs);
        IDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IDInput.setAdapter(IDAdapter);

        //Filling the date_input dropdown with acceptable dates
        List<String> eventDates = Arrays.asList("2024-12-08","2024-12-09","2024-12-10","2024-12-11","2024-12-12","2024-12-13","2024-12-14");
        ArrayAdapter<String> dateAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventDates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateInput.setAdapter(dateAdapter);

        // Populate Event Time dropdown
        List<String> eventTimes = Arrays.asList("8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM","12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM","4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM","8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM","12:00 AM");
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, eventTimes);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeInput.setAdapter(timeAdapter);



        //track the selected dropdown options for the ID, date and time
        IDInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedID = eventIDs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dateInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDate = eventDates.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedDate = null;
            }
        });

        timeInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = eventTimes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    selectedTime = null;
            }
        });
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


        dbManager.insertEvent(title, description, selectedDate, selectedTime);
    }
    public void btnDeleteEventPressed(View v){
        int temp = Integer.parseInt(selectedID);
        dbManager.deleteEvent(temp);
    }

    public void btnUpdateEventPressed(View v){
        int temp = Integer.parseInt(selectedID);
        String title = eventInput.getText().toString();
        String description = descriptionInput.getText().toString();

        dbManager.updateEvent(temp,title,description, selectedDate, selectedTime);
    }
}