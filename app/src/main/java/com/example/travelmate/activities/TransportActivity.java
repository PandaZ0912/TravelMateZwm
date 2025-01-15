package com.example.travelmate.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.adapters.TransportAdapter;
import com.example.travelmate.models.TransportItem;

import java.util.ArrayList;
import java.util.List;

public class TransportActivity extends AppCompatActivity {

    private RecyclerView transportRecyclerView;
    private TransportAdapter transportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        // 获取RecyclerView
        transportRecyclerView = findViewById(R.id.transport_recyclerview);

        // 设置布局管理器
        transportRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化适配器并设置到RecyclerView
        transportAdapter = new TransportAdapter(getTransportItems());
        transportRecyclerView.setAdapter(transportAdapter);
    }

    // 模拟获取交通工具数据
    private List<TransportItem> getTransportItems() {
        List<TransportItem> transportItems = new ArrayList<>();
        transportItems.add(new TransportItem("G101", "2025-01-05 08:30", "2025-01-05 10:30", "北京", "上海", 500, "商务座"));
        transportItems.add(new TransportItem("D202", "2025-01-05 09:00", "2025-01-05 12:00", "广州", "深圳", 300, "一等座"));
        transportItems.add(new TransportItem("Z500", "2025-01-05 12:00", "2025-01-05 18:30", "成都", "西安", 400, "硬卧"));
        transportItems.add(new TransportItem("K104", "2025-01-05 13:30", "2025-01-05 20:30", "武汉", "杭州", 350, "软卧"));
        transportItems.add(new TransportItem("G110", "2025-01-05 15:30", "2025-01-05 17:30", "南京", "上海", 220, "二等座"));
        return transportItems;
    }
}
