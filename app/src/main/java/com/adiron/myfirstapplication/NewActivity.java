package com.adiron.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ImageView
        ImageView timerImage = findViewById(R.id.timerImage);

        // Image resources for countdown
        final int[] images = {
                R.drawable.num_5,
                R.drawable.num_4,
                R.drawable.num_3,
                R.drawable.num_2,
                R.drawable.num_1
        };

        new CountDownTimer(5000, 1000) {
            int i = 0;

            public void onTick(long millisUntilFinished) {
                if (i < images.length) {
                    timerImage.setImageResource(images[i]);
                    i++;
                }
            }

            public void onFinish() {
                Intent intent = new Intent(NewActivity.this, NextActivity.class);
                startActivity(intent);
                finish(); // Optional: Closes NewActivity after switching
            }
        }.start();
    }
}
