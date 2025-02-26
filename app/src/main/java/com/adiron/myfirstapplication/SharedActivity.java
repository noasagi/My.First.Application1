package com.adiron.myfirstapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SharedActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText name, email;
    TextView tvGreeting;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        // חיבור האלמנטים מה-XML
        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        tvGreeting = findViewById(R.id.tvGreeting);

        // יצירת SharedPreferences
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        // בדיקה אם יש נתונים שמורים והצגת הנתונים ב-EditText
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
            updateGreeting();
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }

    // פונקציה לשמירת הנתונים
    public void Save(View view) {
        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.apply();
    }

    // פונקציה לניקוי השדות
    public void clear(View view) {
        name.setText("");
        email.setText("");
        tvGreeting.setText("ברוך הבא!");
    }

    // פונקציה לשליפת הנתונים השמורים ועדכון ההודעה
    public void Get(View view) {
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
            updateGreeting();
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }

    // פונקציה שמעדכנת את הודעת הברכה
    private void updateGreeting() {
        String n = sharedpreferences.getString(Name, "");
        if (!n.isEmpty()) {
            tvGreeting.setText("שלום, " + n + "!");
        } else {
            tvGreeting.setText("ברוך הבא!");
        }
    }
}
