package com.tmsimple.orbical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;
// Additional imports for Add Event
import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    // Variable for the Button object
    private Button open2;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Calls the super class's onCreate method to perform initial setup
        super.onCreate(savedInstanceState);
        // Sets the content view to activity_main2 layout
        setContentView(R.layout.activity_main2);

        // Creates a Calendar instance and format the current date
        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        // Finds the TextView by its ID and set the formatted date
        TextView dateTextView = findViewById(R.id.date_text2);
        dateTextView.setText(dateFormat);

        // Finds the Button by its ID and set an OnClickListener
        open2 = findViewById(R.id.button2);
        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an Intent to start MainActivity when the button is clicked
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Declaring and initializing the event button from the layout file
        //Button mButton = findViewById(R.id.eventbutton);

        // Event start and end time with date
        //String startTime = "2022-02-1T09:00:00";
        //String endTime = "2022-02-1T12:00:00";

        // Parsing the date and time
        //SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //Date mStartTime = null;
        //Date mEndTime = null;
        //try {
        //  mStartTime = mSimpleDateFormat.parse(startTime);
        //  mEndTime = mSimpleDateFormat.parse(endTime);
        //} catch (ParseException e) {
            //e.printStackTrace();
//        }

        // When Button is clicked, Intent started to create an event with the given time
//        mButton.setOnClickListener(new View.OnClickListener() {
  //          @Override
    //        public void onClick(View v) {
      //          Intent mIntent = new Intent(Intent.ACTION_EDIT);
        //        mIntent.setType("vnd.android.cursor.item/event");
          //      if (mStartTime != null && mEndTime != null) {
            //        mIntent.putExtra("beginTime", mStartTime.getTime());
              //      mIntent.putExtra("time", true);
                //    mIntent.putExtra("rule", "FREQ=YEARLY");
                  //  mIntent.putExtra("endTime", mEndTime.getTime());
                    //mIntent.putExtra("title", "Geeksforgeeks Event");
                //}
                //startActivity(mIntent);
            //}
        //});
    }
}