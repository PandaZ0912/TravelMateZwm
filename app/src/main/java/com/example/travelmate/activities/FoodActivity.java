package com.example.travelmate.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.adapters.LeftCategoryAdapter;
import com.example.travelmate.adapters.RightFoodAdapter;
import com.example.travelmate.models.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private LeftCategoryAdapter leftCategoryAdapter;
    private RightFoodAdapter rightFoodAdapter;

    private List<String> cities = new ArrayList<>();
    private List<FoodItem> foodItems = new ArrayList<>();

    // 预定义的城市和对应的美食数据
    private final String[] cityList = {"北京", "上海", "广州", "成都"};
    private final List<FoodItem> beijingFood = new ArrayList<>();
    private final List<FoodItem> shanghaiFood = new ArrayList<>();
    private final List<FoodItem> guangzhouFood = new ArrayList<>();
    private final List<FoodItem> chengduFood = new ArrayList<>();
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        // 初始化UI组件
        leftRecyclerView = findViewById(R.id.left_recyclerview);
        rightRecyclerView = findViewById(R.id.right_recyclerview);

        // 设置RecyclerView的LayoutManager
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 填充城市列表
        for (String city : cityList) {
            cities.add(city);
        }

        // 初始化美食数据
        initFoodData();

        // 设置左侧城市适配器
        leftCategoryAdapter = new LeftCategoryAdapter(cities);
        leftRecyclerView.setAdapter(leftCategoryAdapter);

        // 设置右侧美食适配器
        rightFoodAdapter = new RightFoodAdapter(foodItems);
        rightRecyclerView.setAdapter(rightFoodAdapter);

        // 处理左侧城市点击事件
        leftCategoryAdapter.setOnCategoryClickListener(new LeftCategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(String city) {
                loadFoodItemsByCity(city);
            }
        });

        // 默认加载北京的美食
        loadFoodItemsByCity("北京");

        backButton = findViewById(R.id.back_button);
        // 设置返回按钮点击事件
        backButton.setOnClickListener(view -> {
            onBackPressed();  // 调用系统的返回操作
        });
    }

    // 初始化美食数据
    private void initFoodData() {
        // 北京美食
        beijingFood.add(new FoodItem("北京炸酱面", "传统的北京美食", 4.8f, 30.0, R.drawable.peking_duck));
        beijingFood.add(new FoodItem("北京烤鸭", "世界著名的北京美食", 4.9f, 80.0, R.drawable.peking_duck));

        // 上海美食
        shanghaiFood.add(new FoodItem("小笼包", "上海著名的点心", 4.7f, 35.0, R.drawable.peking_duck));
        shanghaiFood.add(new FoodItem("生煎包", "美味的上海生煎包", 4.6f, 25.0, R.drawable.peking_duck));

        // 广州美食
        guangzhouFood.add(new FoodItem("肠粉", "传统广州美食", 4.8f, 28.0, R.drawable.peking_duck));
        guangzhouFood.add(new FoodItem("白切鸡", "广东特色白切鸡", 4.7f, 50.0, R.drawable.peking_duck));

        // 成都美食
        chengduFood.add(new FoodItem("火锅", "麻辣鲜香的成都火锅", 5.0f, 100.0, R.drawable.peking_duck));
        chengduFood.add(new FoodItem("兔头", "香辣的成都特色美食", 4.9f, 50.0, R.drawable.peking_duck));
    }

    // 根据城市加载美食数据
    private void loadFoodItemsByCity(String city) {
        switch (city) {
            case "北京":
                rightFoodAdapter.updateFoodList(beijingFood);
                break;
            case "上海":
                rightFoodAdapter.updateFoodList(shanghaiFood);
                break;
            case "广州":
                rightFoodAdapter.updateFoodList(guangzhouFood);
                break;
            case "成都":
                rightFoodAdapter.updateFoodList(chengduFood);
                break;
            default:
                Toast.makeText(FoodActivity.this, "未知城市", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
