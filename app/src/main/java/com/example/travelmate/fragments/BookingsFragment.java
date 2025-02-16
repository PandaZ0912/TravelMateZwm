package com.example.travelmate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelmate.R;
import com.example.travelmate.activities.OrderDetailsActivity;
import com.example.travelmate.activities.TravelDetailsActivity;
import com.example.travelmate.adapters.OrderAdapter;
import com.example.travelmate.adapters.TravelListAdapter;
import com.example.travelmate.models.*;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BookingsFragment extends Fragment {

    private RecyclerView rvTravelList, rvOrderList;
    private TravelListAdapter travelListAdapter;
    private OrderAdapter orderAdapter;
    private List<Travel> travelList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        // 获取 TabLayout 和 RecyclerView
        tabLayout = view.findViewById(R.id.tab_layout);
        rvTravelList = view.findViewById(R.id.rv_travel_list);
        rvOrderList = view.findViewById(R.id.rv_order_list);

        // 设置 RecyclerView 的布局管理器
        rvTravelList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrderList.setLayoutManager(new LinearLayoutManager(getContext()));

        // 初始化数据
        loadSampleData();

        // 设置适配器并监听点击事件
        travelListAdapter = new TravelListAdapter(travelList, new TravelListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Travel travel) {
                // 当点击行程卡片时，跳转到 TravelDetailsActivity
                Intent intent = new Intent(getContext(), TravelDetailsActivity.class);
                intent.putExtra("TRAVEL_DATA", travel);  // 将 Travel 对象传递到目标 Activity
                startActivity(intent);
            }
        });
        rvTravelList.setAdapter(travelListAdapter);

        // 设置订单列表适配器
        orderAdapter = new OrderAdapter(orderList, order -> {
            Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
            intent.putExtra("ORDER_DATA", order);  // 将 Order 对象传递到目标 Activity
            startActivity(intent);
        });
        rvOrderList.setAdapter(orderAdapter);

        // 设置 TabLayout 切换监听器
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: // 行程查询
                        rvTravelList.setVisibility(View.VISIBLE);
                        rvOrderList.setVisibility(View.GONE);
                        break;
                    case 1: // 订单查询
                        rvTravelList.setVisibility(View.GONE);
                        rvOrderList.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return view;
    }

    // 加载示例数据
    private void loadSampleData() {
        // 创建图片数据
        List<ImageData> images1 = new ArrayList<>();
        images1.add(new ImageData(R.drawable.huanghelou, ImageData.ImageSource.DRAWABLE));

        List<ImageData> images2 = new ArrayList<>();
        images2.add(new ImageData(R.drawable.wuhanuniversity, ImageData.ImageSource.DRAWABLE));

        List<ImageData> images3 = new ArrayList<>();
        images3.add(new ImageData(R.drawable.hubeimuseum, ImageData.ImageSource.DRAWABLE));

        List<ImageData> images4 = new ArrayList<>();
        images4.add(new ImageData(R.drawable.wuhan, ImageData.ImageSource.DRAWABLE));

        List<ImageData> images5 = new ArrayList<>();
        images5.add(new ImageData(R.drawable.reganmian, ImageData.ImageSource.DRAWABLE));

        List<ImageData> images6 = new ArrayList<>();
        images6.add(new ImageData(R.drawable.wuhanzhiwuyuan, ImageData.ImageSource.DRAWABLE));

        // 创建活动对象（武汉）
        Activity activity1 = new Activity("9:00 - 10:30", "游览黄鹤楼", "黄鹤楼", "游览武汉的地标性建筑，欣赏武汉的美丽城市风光", images1);
        Activity activity2 = new Activity("11:00 - 12:30", "游览武汉大学", "武汉大学", "参观中国最美的大学之一，领略其悠久的历史与文化", images2);
        Activity activity3 = new Activity("14:00 - 16:00", "参观湖北省博物馆", "湖北省博物馆", "探索湖北省的历史和文化，了解不同的文物与艺术", images3);

        Activity activity4 = new Activity("9:00 - 11:00", "游览长江大桥", "长江大桥", "体验世界著名的长江大桥，感受江水的澎湃和桥梁的壮丽", images4);
        Activity activity5 = new Activity("12:00 - 14:00", "午餐：汉口老街", "汉口老街", "在武汉最具历史感的老街品尝地道的武汉美食小吃", images5);
        Activity activity6 = new Activity("15:00 - 17:00", "游览武汉植物园", "武汉植物园", "在武汉植物园内欣赏自然景观，呼吸新鲜空气，放松心情", images6);

        // 使用 Drawable 资源创建活动
        List<Activity> day1Activities = new ArrayList<>();
        day1Activities.add(activity1);
        day1Activities.add(activity2);
        day1Activities.add(activity3);

        // 创建第二天活动
        List<Activity> day2Activities = new ArrayList<>();
        day2Activities.add(activity4);
        day2Activities.add(activity5);
        day2Activities.add(activity6);

        // 创建 DayPlan 和 Travel 对象
        List<DayPlan> dayPlans = new ArrayList<>();
        dayPlans.add(new DayPlan("3月24日", day1Activities));
        dayPlans.add(new DayPlan("3月25日", day2Activities));

        // 第一个行程：武汉
        Travel travel1 = new Travel("武汉", "3月24日 - 3月26日", R.drawable.wuhan, dayPlans);
        travelList.add(travel1);

        // 创建活动对象（杭州）
        Activity activity9 = new Activity("9:00 - 10:30", "游览西湖", "西湖", "在西湖边散步，欣赏美丽的湖光山色，品味杭州的自然之美", images1);
        Activity activity10 = new Activity("11:00 - 12:30", "游览雷峰塔", "雷峰塔", "参观古老的雷峰塔，聆听西湖传说，俯瞰西湖全景", images2);
        Activity activity11 = new Activity("14:00 - 16:00", "游览灵隐寺", "灵隐寺", "参拜灵隐寺，体验佛教文化的宁静与深远", images3);

        // 杭州的其他景点与活动
        Activity activity12 = new Activity("9:00 - 11:00", "西溪湿地公园", "西溪湿地", "游走在西溪湿地的自然保护区，放松身心，亲近大自然", images4);
        Activity activity13 = new Activity("12:00 - 14:00", "午餐：龙井村", "龙井村", "在龙井村品尝正宗的龙井茶和地道的杭州美食", images5);
        Activity activity14 = new Activity("15:00 - 17:00", "杭州植物园", "杭州植物园", "在杭州植物园欣赏多样的植物品种和美丽的园林景观", images6);

        // 使用 Drawable 资源创建活动
        List<Activity> day1Activities2 = new ArrayList<>();
        day1Activities2.add(activity9);
        day1Activities2.add(activity10);
        day1Activities2.add(activity11);

        // 创建第二天活动
        List<Activity> day2Activities2 = new ArrayList<>();
        day2Activities2.add(activity12);
        day2Activities2.add(activity13);
        day2Activities2.add(activity14);

        // 创建第二个行程的 DayPlan 和 Travel 对象
        List<DayPlan> dayPlans2 = new ArrayList<>();
        dayPlans2.add(new DayPlan("4月1日", day1Activities2));
        dayPlans2.add(new DayPlan("4月2日", day2Activities2));

        Travel travel2 = new Travel("杭州", "4月1日 - 4月3日", R.drawable.hangzhou, dayPlans2);
        travelList.add(travel2);

        // 示例订单数据
        orderList.add(new Order("武汉到北京机票", "2024年3月24日", "$300",Order.OrderType.TRANSPORT));
        orderList.add(new Order("武汉大学酒店预订", "2024年3月24日 - 2024年3月25日", "$120",Order.OrderType.HOTEL));
        orderList.add(new Order("黄鹤楼景区门票", "2024年3月24日", "$50",Order.OrderType.TICKET));
    }

}
