package com.example.travelmate.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.adapters.LeftCategoryAdapter;
import com.example.travelmate.adapters.RightHotelAdapter;
import com.example.travelmate.models.HotelItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

    private LeftCategoryAdapter leftCategoryAdapter;
    private RightHotelAdapter rightHotelAdapter;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        // 初始化左侧分类RecyclerView
        leftRecyclerView = findViewById(R.id.left_recyclerview);
        // 初始化右侧酒店信息RecyclerView
        rightRecyclerView = findViewById(R.id.right_recyclerview);

        // 设置RecyclerView的布局管理器
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 设置左侧分类数据
        leftCategoryAdapter = new LeftCategoryAdapter(getCategoryList());
        leftRecyclerView.setAdapter(leftCategoryAdapter);

        // 设置右侧酒店列表数据，默认显示北京的酒店
        rightHotelAdapter = new RightHotelAdapter(getHotels("北京"));
        rightRecyclerView.setAdapter(rightHotelAdapter);

        // 左侧分类点击监听
        leftCategoryAdapter.setOnCategoryClickListener(category -> {
            // 根据选中的城市分类更新右侧酒店列表
            rightHotelAdapter.updateHotels(getHotels(category));
        });

        backButton = findViewById(R.id.back_button);
        // 设置返回按钮点击事件
        backButton.setOnClickListener(view -> {
            onBackPressed();  // 调用系统的返回操作
        });
    }

    // 模拟获取城市分类列表
    private List<String> getCategoryList() {
        return Arrays.asList("北京", "上海", "广州", "深圳");
    }

    // 模拟根据城市获取酒店列表
    private List<HotelItem> getHotels(String city) {
        List<HotelItem> hotels = new ArrayList<>();
        switch (city) {
            case "北京":
                hotels.add(new HotelItem("北京四季酒店", "五星级豪华酒店", R.drawable.fourseasons_beijing, 4.7, 1200, "北京市朝阳区丽都大厦", "五星级"));
                hotels.add(new HotelItem("北京朝阳酒店", "四星级舒适酒店", R.drawable.beijing_chaoyang_hotel, 4.3, 800, "北京市朝阳区建国门外大街", "四星级"));
                break;
            case "上海":
                hotels.add(new HotelItem("上海外滩酒店", "享有外滩美景", R.drawable.hyatt_bund_shanghai, 4.9, 1500, "上海市黄浦区外滩", "五星级"));
                hotels.add(new HotelItem("上海浦东机场酒店", "便捷的机场酒店", R.drawable.shanghai_pudong_airport_hotel, 4.2, 600, "上海市浦东新区机场", "三星级"));
                break;
            case "广州":
                hotels.add(new HotelItem("广州塔酒店", "高端的城市地标酒店", R.drawable.guangzhou_tower_hotel, 4.6, 1000, "广州市天汇大街", "五星级"));
                hotels.add(new HotelItem("广州白云酒店", "城市便捷酒店", R.drawable.guangzhou_baiyun_hotel, 4.1, 500, "广州市白云山路", "三星级"));
                break;
            case "深圳":
                hotels.add(new HotelItem("深圳湾大酒店", "豪华海景酒店", R.drawable.shenzhen_bay_hotel, 4.8, 1800, "深圳市南山区滨海大道", "五星级"));
                hotels.add(new HotelItem("深圳国际机场酒店", "便捷机场酒店", R.drawable.shenzhen_international_airport_hotel, 4.4, 700, "深圳市宝安区机场路", "四星级"));
                break;
            default:
                Toast.makeText(this, "未找到该城市的酒店", Toast.LENGTH_SHORT).show();
                break;
        }
        return hotels;
    }
}
