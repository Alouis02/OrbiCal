package com.tmsimple.orbical;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private Button open2;

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
    }
}