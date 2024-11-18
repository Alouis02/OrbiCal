package com.tmsimple.orbical;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;
//comment added
public class MainActivity extends AppCompatActivity {
    private Button open;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView dateTextView = findViewById(R.id.date_text);
        dateTextView.setText(dateFormat);

        imageView = findViewById(R.id.monday);
        imageView = findViewById(R.id.tuesday);
        imageView = findViewById(R.id.wednesday);
        imageView = findViewById(R.id.thursday);
        imageView = findViewById(R.id.friday);
        imageView = findViewById(R.id.saturday);
        imageView = findViewById(R.id.sunday);
        Calendar cal = Calendar.getInstance();

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            imageView.setImageResource(R.drawable.monday);
        }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            imageView.setImageResource(R.drawable.tuesday);
        }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            imageView.setImageResource(R.drawable.wednesday);
        }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            imageView.setImageResource(R.drawable.thursday);
        }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            imageView.setImageResource(R.drawable.friday);
        }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            imageView.setImageResource(R.drawable.saturday);
        }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            imageView.setImageResource(R.drawable.sunday);
        }

        open = findViewById(R.id.button1);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}