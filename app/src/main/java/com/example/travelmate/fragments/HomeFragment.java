package com.example.travelmate.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.travelmate.R;
import com.example.travelmate.TravelMateApplication;
import com.example.travelmate.adapters.AdViewPagerAdapter;
import com.example.travelmate.adapters.BoardAdapter;
import com.example.travelmate.adapters.ModuleAdapter;
import com.example.travelmate.adapters.RankAdapter;
import com.example.travelmate.data.DatabaseHelper;
import com.example.travelmate.models.BoardItem;
import com.example.travelmate.models.ModuleModel;
import com.example.travelmate.models.RankItem;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager2;
    private CircularProgressIndicator progressIndicator;
    private RecyclerView modulesRecyclerView;
    private RecyclerView rankingsRecyclerView;
    private Handler handler = new Handler();
    private List<Integer> imageResources = Arrays.asList(
            R.drawable.paris, R.drawable.tokyo, R.drawable.new_york);

    // 模块数据
    private List<ModuleModel> moduleList = Arrays.asList(
            new ModuleModel("酒店民宿", "预定酒店和民宿", R.drawable.ic_hotel_icon, "com.example.travelmate.activities.HotelActivity"),
            new ModuleModel("交通票务", "查看机票和火车票", R.drawable.ic_flight_icon, "com.example.travelmate.activities.TransportActivity"),
            new ModuleModel("景区门票", "购买景区门票", R.drawable.ic_ticket_icon, "com.example.travelmate.activities.TicketActivity"),
            new ModuleModel("美食在线", "查看美食推荐", R.drawable.ic_food_icon, "com.example.travelmate.activities.FoodActivity"),
            new ModuleModel("旅行日志", "记录旅行日志", R.drawable.ic_log_icon, "com.example.travelmate.activities.TravelLogActivity")
    );

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化 ViewPager2 并设置适配器
        viewPager2 = view.findViewById(R.id.adViewPager);
        AdViewPagerAdapter adViewPagerAdapter = new AdViewPagerAdapter(getContext(), imageResources);
        viewPager2.setAdapter(adViewPagerAdapter);

        // 初始化进度条
        progressIndicator = view.findViewById(R.id.progress_indicator);
        progressIndicator.setVisibility(View.VISIBLE);

        // 初始化模块 RecyclerView
        modulesRecyclerView = view.findViewById(R.id.modulesRecyclerView);
        modulesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ModuleAdapter moduleAdapter = new ModuleAdapter(moduleList);
        modulesRecyclerView.setAdapter(moduleAdapter);

        // 初始化榜单 RecyclerView
        rankingsRecyclerView = view.findViewById(R.id.rankingsRecyclerView);
        rankingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseHelper databaseHelper = new DatabaseHelper(TravelMateApplication.getAppContext());
        List<BoardItem> boardItemList = databaseHelper.getAllBoardItems(getContext());
        BoardAdapter boardAdapter = new BoardAdapter(getContext(), boardItemList);
        rankingsRecyclerView.setAdapter(boardAdapter);

        // 启动自动滑动
        startAutoSlide();

        return view;
    }

    // 自动轮播功能
    private void startAutoSlide() {
        final int delayMillis = 3000;
        final int intervalMillis = 3000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int nextItem = (viewPager2.getCurrentItem() + 1) % viewPager2.getAdapter().getItemCount();
                viewPager2.setCurrentItem(nextItem, true);
                handler.postDelayed(this, intervalMillis);
            }
        }, delayMillis);
    }
}