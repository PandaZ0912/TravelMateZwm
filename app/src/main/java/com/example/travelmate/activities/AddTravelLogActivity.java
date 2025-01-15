package com.example.travelmate.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.data.DatabaseHelper;

public class AddTravelLogActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText titleEditText, contentEditText, locationEditText, dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_log);

        dbHelper = new DatabaseHelper(this);
        titleEditText = findViewById(R.id.log_title);
        contentEditText = findViewById(R.id.log_content);
        locationEditText = findViewById(R.id.log_location);
        dateEditText = findViewById(R.id.log_date);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> saveTravelLog());
    }

    private void saveTravelLog() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String date = dateEditText.getText().toString();

        // 假设用户ID为1
        int userId = 1;
        boolean success = dbHelper.insertTravelLog(userId, title, content, location, date, null);

        if (success) {
            Toast.makeText(this, "旅行日志已添加", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }
}
