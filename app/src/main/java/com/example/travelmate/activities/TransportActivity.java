package com.example.travelmate.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageButton;

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
    private EditText searchEditText;
    private Spinner departureSpinner, arrivalSpinner;
    private Button searchButton;
    private ImageButton backButton;

    private List<String> departureList;
    private List<String> arrivalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        // 获取视图组件
        transportRecyclerView = findViewById(R.id.transport_recyclerview);
        searchEditText = findViewById(R.id.search_edittext);
        departureSpinner = findViewById(R.id.spinner_departure);
        arrivalSpinner = findViewById(R.id.spinner_arrival);
        searchButton = findViewById(R.id.query_button);
        backButton = findViewById(R.id.back_button);

        // 设置RecyclerView布局管理器
        transportRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化适配器并设置到RecyclerView
        transportAdapter = new TransportAdapter(TransportActivity.this,getTransportItems());
        transportRecyclerView.setAdapter(transportAdapter);

        // 设置出发地点和到达地点的Spinner
        departureList = getDepartureList();
        arrivalList = getArrivalList();

        // 在列表中添加默认的 "请选择" 选项
        departureList.add(0, "请选择");
        arrivalList.add(0, "请选择");

        ArrayAdapter<String> departureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departureList);
        departureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureSpinner.setAdapter(departureAdapter);

        ArrayAdapter<String> arrivalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrivalList);
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrivalSpinner.setAdapter(arrivalAdapter);

        // 设置查询按钮点击事件
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String departure = departureSpinner.getSelectedItem().toString();
                String arrival = arrivalSpinner.getSelectedItem().toString();
                String query = searchEditText.getText().toString();

                // 在这里你可以根据查询条件进行数据过滤
                List<TransportItem> filteredItems = filterTransportItems(departure, arrival, query);
                transportAdapter = new TransportAdapter(TransportActivity.this, filteredItems);
                transportRecyclerView.setAdapter(transportAdapter);
            }
        });

        // 设置返回按钮点击事件，返回上一个活动
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 结束当前活动，返回到上一个活动
            }
        });
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

    // 获取出发地点列表
    private List<String> getDepartureList() {
        List<String> departures = new ArrayList<>();
        departures.add("北京");
        departures.add("广州");
        departures.add("成都");
        departures.add("武汉");
        departures.add("南京");
        return departures;
    }

    // 获取到达地点列表
    private List<String> getArrivalList() {
        List<String> arrivals = new ArrayList<>();
        arrivals.add("上海");
        arrivals.add("深圳");
        arrivals.add("西安");
        arrivals.add("杭州");
        arrivals.add("广州");
        return arrivals;
    }

    // 根据出发地、到达地和查询条件过滤数据
    private List<TransportItem> filterTransportItems(String departure, String arrival, String query) {
        List<TransportItem> filteredItems = new ArrayList<>();
        for (TransportItem item : getTransportItems()) {
            // 如果出发地或到达地是"请选择"，则不进行过滤，返回所有交通工具
            if ((departure.equals("请选择") || item.getDeparture().contains(departure)) &&
                    (arrival.equals("请选择") || item.getArrival().contains(arrival)) &&
                    (item.getTrainNo().contains(query) || item.getSeatType().contains(query))) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}
