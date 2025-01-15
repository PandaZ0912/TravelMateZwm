package com.example.travelmate.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.adapters.DayPlanAdapter;
import com.example.travelmate.models.Travel;

public class TravelDetailsActivity extends AppCompatActivity {

    private RecyclerView rvDayPlans;
    private DayPlanAdapter dayPlanAdapter;
    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_details);

        // 获取传递的 Travel 对象
        // 获取传递过来的 Travel 对象
        Travel travel = getIntent().getParcelableExtra("TRAVEL_DATA");

        if (travel != null) {
            // 处理 Travel 对象，例如展示行程信息
            Log.d("TravelDetails", "Destination: " + travel.getDestination());
        } else {
            Log.e("TravelDetails", "Received Travel object is null!");
        }

        // 初始化 RecyclerView 和适配器
        rvDayPlans = findViewById(R.id.rv_day_plans);
        rvDayPlans.setLayoutManager(new LinearLayoutManager(this));

        // 设置适配器
        dayPlanAdapter = new DayPlanAdapter(travel.getDayPlans());
        rvDayPlans.setAdapter(dayPlanAdapter);

        // 设置返回按钮的点击事件
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> onBackPressed());  // 调用 onBackPressed() 返回到上一个页面
    }
}
