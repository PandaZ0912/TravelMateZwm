package com.example.travelmate.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.adapters.LeftCategoryAdapter;
import com.example.travelmate.adapters.RightTicketAdapter;
import com.example.travelmate.models.TicketItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView, rightRecyclerView;
    private LeftCategoryAdapter leftCategoryAdapter;
    private RightTicketAdapter rightTicketAdapter;

    private Map<String, List<TicketItem>> categoryData;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        leftRecyclerView = findViewById(R.id.left_recyclerview);
        rightRecyclerView = findViewById(R.id.right_recyclerview);

        // 初始化左侧分类的数据
        List<String> categories = getCategories();
        leftCategoryAdapter = new LeftCategoryAdapter(categories);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        leftRecyclerView.setAdapter(leftCategoryAdapter);

        // 初始化右侧的适配器，默认显示第一个分类的数据（例如，北京）
        updateRightRecyclerView("北京");

        // 监听左侧分类点击事件
        leftCategoryAdapter.setOnCategoryClickListener(category -> updateRightRecyclerView(category));

        backButton = findViewById(R.id.back_button);
        // 设置返回按钮点击事件
        backButton.setOnClickListener(view -> {
            onBackPressed();  // 调用系统的返回操作
        });
    }

    // 获取分类列表
    private List<String> getCategories() {
        return new ArrayList<>(getCategoryData().keySet());
    }

    // 获取分类与景区数据的映射
    private Map<String, List<TicketItem>> getCategoryData() {
        if (categoryData == null) {
            categoryData = new HashMap<>();

            // 北京省份的景区
            List<TicketItem> beijingTickets = new ArrayList<>();
            beijingTickets.add(new TicketItem("故宫", "北京的故宫博物院，是世界上最大的宫殿建筑群之一。", R.drawable.ic_ticket_icon, 60.0, 4.7f));
            beijingTickets.add(new TicketItem("长城", "世界著名的长城，壮丽的古代建筑。", R.drawable.ic_ticket_icon, 80.0, 4.5f));

            // 上海省份的景区
            List<TicketItem> shanghaiTickets = new ArrayList<>();
            shanghaiTickets.add(new TicketItem("东方明珠", "上海的标志性建筑，提供俯瞰城市的美丽景观。", R.drawable.ic_ticket_icon, 120.0, 4.6f));
            shanghaiTickets.add(new TicketItem("上海迪士尼", "全球著名的主题公园，适合全家游玩。", R.drawable.ic_ticket_icon, 499.0, 4.8f));

            // 云南省份的景区
            List<TicketItem> yunnanTickets = new ArrayList<>();
            yunnanTickets.add(new TicketItem("大理古城", "大理古城，保存完好的古老城市，拥有丰富的历史文化。", R.drawable.ic_ticket_icon, 50.0, 4.3f));
            yunnanTickets.add(new TicketItem("丽江古城", "丽江古城是中国最具历史意义的古代城市之一。", R.drawable.ic_ticket_icon, 60.0, 4.4f));

            // 将数据加入到分类中
            categoryData.put("北京", beijingTickets);
            categoryData.put("上海", shanghaiTickets);
            categoryData.put("云南", yunnanTickets);
        }
        return categoryData;
    }

    // 更新右侧的门票信息
    private void updateRightRecyclerView(String category) {
        List<TicketItem> ticketList = getCategoryData().get(category);
        rightTicketAdapter = new RightTicketAdapter(ticketList);
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setAdapter(rightTicketAdapter);
    }
}
