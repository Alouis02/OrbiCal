package com.tmsimple.orbical;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This calls the super class's onCreate method to perform initial setup
        super.onCreate(savedInstanceState);
        // This sets the content view to activity_main3 layout
        setContentView(R.layout.activity_main3);

        // Creates a new Handler to post a delayed Runnable
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Creates an Intent to start MainActivity after the delay
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                // Finishes SplashScreen activity so it's removed from the back stack
                finish();
            }
        }, 3000); // Delays of 3000 milliseconds (3 seconds)
    }
}