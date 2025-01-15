package com.example.travelmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.adapters.TravelLogAdapter;
import com.example.travelmate.data.DatabaseHelper;
import com.example.travelmate.models.TravelLog;

import java.util.List;

public class TravelLogActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView travelLogListView;
    private TravelLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_log);

        dbHelper = new DatabaseHelper(this);
        travelLogListView = findViewById(R.id.travel_log_list_view);

        // 获取所有旅行日志
        int userId = 1; // 假设当前用户ID为1
        List<TravelLog> travelLogs = dbHelper.getAllTravelLogs(userId);

        // 创建适配器
        adapter = new TravelLogAdapter(this, travelLogs);
        travelLogListView.setAdapter(adapter);

        // 设置点击事件，进入编辑页面
        travelLogListView.setOnItemClickListener((parent, view, position, id) -> {
            TravelLog log = (TravelLog) adapter.getItem(position);
            Intent intent = new Intent(TravelLogActivity.this, EditTravelLogActivity.class);
            intent.putExtra("logId", log.getId());
            startActivity(intent);
        });

        // 添加旅行日志按钮
        Button addButton = findViewById(R.id.add_travel_log_button);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(TravelLogActivity.this, AddTravelLogActivity.class);
            startActivity(intent);
        });
    }
}
