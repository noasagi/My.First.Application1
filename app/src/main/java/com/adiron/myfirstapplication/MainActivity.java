package com.adiron.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Button linear, button5, guess;
    CheckBox button, button2, button3;
    TextView tv1;
    Switch s;
    ConstraintLayout constraintLayout;
    SeekBar sb;
    TextView emojiTrump1, emojiTrump2;

    Menu menu;

    MenuItem item;
    private boolean isAccessToGameAllowed = false; // משתנה לעקוב אחר אישור הגישה

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        linear = findViewById(R.id.linear);
        button5 = findViewById(R.id.button5);
        guess = findViewById(R.id.guess);

        tv1 = findViewById(R.id.textView);
        s = findViewById(R.id.s);
        constraintLayout = findViewById(R.id.constraintLayout);
        emojiTrump1 = findViewById(R.id.emojiTrump1);
        emojiTrump2 = findViewById(R.id.emojiTrump2);
        sb = findViewById(R.id.sb);

        button.setOnClickListener(view -> {
            tv1.setText("Later today");
            Log.d("Adi", "button clicked");
        });

        button2.setOnClickListener(view -> {
            tv1.setText("Absolutely!");
            Log.d("Adi", "button2 clicked");
        });

        button3.setOnClickListener(view -> {
            tv1.setText("Not today");
            Log.d("Adi", "button3 clicked");
        });

        guess.setOnClickListener(view -> {
            if (isAccessToGameAllowed) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "You must confirm the checkbox in ButtonActivity first.", Toast.LENGTH_SHORT).show();
            }
        });

        linear.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LinearActivity.class);
            startActivity(intent);
            finish();
        });

        button5.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ButtonActivity.class);
            startActivityForResult(intent, 1);
        });

        s.setOnCheckedChangeListener(this);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float alpha = (float) i / 100;
                emojiTrump1.setAlpha(alpha);
                emojiTrump2.setAlpha(1 - alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            boolean confirmationStatus = data.getBooleanExtra("confirmationStatus", false);
            if (confirmationStatus) {
                isAccessToGameAllowed = true; // מאשר גישה למשחק
                Toast.makeText(this, "Access to the game is now allowed.", Toast.LENGTH_SHORT).show();
            } else {
                isAccessToGameAllowed = false; // גישה לא מאושרת
                Toast.makeText(this, "Access to the game is still restricted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            constraintLayout.setBackgroundColor(Color.WHITE);
            s.setText("Off");
        } else {
            constraintLayout.setBackgroundColor(Color.parseColor("#D5FA8DBC"));
            s.setText("On");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_login){
            Toast.makeText(this, "you selected login", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.action_register){
            Toast.makeText(this, "you selected register", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.action_start){
            Toast.makeText(this, "you selected start", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.new_item) {
            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
        }
        return true;
    }

    }
