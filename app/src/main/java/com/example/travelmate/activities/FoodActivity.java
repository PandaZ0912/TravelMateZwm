package com.example.travelmate.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.adapters.FoodAdapter;
import com.example.travelmate.models.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // 获取RecyclerView
        foodRecyclerView = findViewById(R.id.food_recyclerview);

        // 设置布局管理器
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化适配器并设置到RecyclerView
        foodAdapter = new FoodAdapter(getFoodItems());
        foodRecyclerView.setAdapter(foodAdapter);
    }

    // 模拟获取美食列表
    private List<FoodItem> getFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("北京烤鸭", "正宗的北京烤鸭，皮脆肉嫩", R.drawable.ic_food_icon, 4.5, 198));
        foodItems.add(new FoodItem("火锅", "麻辣火锅，搭配各式配菜", R.drawable.ic_food_icon, 4.0, 128));
        foodItems.add(new FoodItem("寿司", "新鲜美味的寿司，适合各种口味", R.drawable.ic_food_icon, 4.7, 88));
        foodItems.add(new FoodItem("披萨", "经典披萨，满满芝士", R.drawable.ic_food_icon, 4.3, 99));
        foodItems.add(new FoodItem("炸鸡", "外脆里嫩的炸鸡，绝配啤酒", R.drawable.ic_food_icon, 4.6, 58));
        return foodItems;
    }
}
