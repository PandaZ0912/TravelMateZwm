package com.example.travelmate.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.data.DatabaseHelper;
import com.example.travelmate.models.TravelLog;

public class EditTravelLogActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText titleEditText, contentEditText, locationEditText, dateEditText;
    private int logId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_travel_log);

        dbHelper = new DatabaseHelper(this);
        titleEditText = findViewById(R.id.log_title);
        contentEditText = findViewById(R.id.log_content);
        locationEditText = findViewById(R.id.log_location);
        dateEditText = findViewById(R.id.log_date);

        logId = getIntent().getIntExtra("logId", -1);

        if (logId != -1) {
            loadLogData(logId);
        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> saveTravelLog());
    }

    private void loadLogData(int logId) {
        TravelLog log = dbHelper.getTravelLogById(logId);

        titleEditText.setText(log.getTitle());
        contentEditText.setText(log.getContent());
        locationEditText.setText(log.getLocation());
        dateEditText.setText(log.getDate());
    }

    private void saveTravelLog() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String date = dateEditText.getText().toString();

        boolean success = dbHelper.updateTravelLog(logId, title, content, location, date);

        if (success) {
            Toast.makeText(this, "旅行日志已更新", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "更新失败", Toast.LENGTH_SHORT).show();
        }
    }
}

