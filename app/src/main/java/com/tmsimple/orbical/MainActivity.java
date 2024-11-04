package com.tmsimple.orbical;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        String dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView dateTextView = findViewById(R.id.date_text);
        dateTextView.setText(dateFormat);

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