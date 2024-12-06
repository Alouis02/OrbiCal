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
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private Button open2;
    private Button eventButton;
    private EditText eventInput;
    private EditText dateInput;
    private EditText timeInput;
    private EditText descriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
    }
}