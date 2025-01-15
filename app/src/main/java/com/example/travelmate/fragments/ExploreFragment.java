package com.example.travelmate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.Post;
import com.example.travelmate.models.User;
import com.example.travelmate.adapters.PostAdapter;
import com.example.travelmate.activities.PostDetailActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExploreFragment extends Fragment {

    private LinearLayout categoriesContainer;  // 用于动态加载所有类别
    private List<Post> postsList;
    private List<User> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        categoriesContainer = view.findViewById(R.id.categories_container);  // 容器视图，存放所有类别部分

        // 初始化数据
        initializeData();

        // 动态加载每个类别部分
        addCategorySection("热门旅行日志", R.drawable.ic_fire_icon, R.color.hotBackground, R.color.hotTextColor, R.color.primaryRColor);
        addCategorySection("景区推荐", R.drawable.ic_city_icon, R.color.spotBackground, R.color.spotTextColor, R.color.spotIconColor);
        addCategorySection("美食推荐", R.drawable.ic_food_icon, R.color.foodBackground, R.color.foodTextColor, R.color.foodIconColor);
        addCategorySection("民宿推荐", R.drawable.ic_hotel_icon, R.color.hotelBackground, R.color.hotelTextColor, R.color.hotelIconColor);

        return view;
    }

    private void initializeData() {
        // 示例用户数据
        usersList = new ArrayList<>();
        usersList.add(new User(1, "user1", "13800000001", "xiaoming@example.com", "encryptedPassword1", "salt1"));
        usersList.add(new User(2, "user2", "13800000002", "xiaohong@example.com", "encryptedPassword2", "salt2"));
        usersList.add(new User(3, "user3", "13800000003", "xiaoli@example.com", "encryptedPassword3", "salt3"));
        usersList.add(new User(4, "user4", "13800000004", "xiaowang@example.com", "encryptedPassword4", "salt4"));
        usersList.add(new User(5, "user5", "13800000005", "xiaozhang@example.com", "encryptedPassword5", "salt5"));
        usersList.add(new User(6, "user6", "13800000006", "xiaozhao@example.com", "encryptedPassword6", "salt6"));

        // 示例帖子数据
        postsList = new ArrayList<>();
        postsList.add(new Post("热门旅行日志", "如何规划一次完美的旅行",
                "这是一篇关于如何规划旅行的帖子，包含了很多实用的建议和方法，帮助你更好地规划你的旅行行程，包括预算管理、行程安排等...",
                "user1", "旅行是人生的一部分，它不仅是身心的放松，也是心灵的洗礼。为了让你的旅行更加完美，这篇文章将带你走进旅行规划的世界...",
                Arrays.asList(R.drawable.post_budget), null,System.currentTimeMillis()));

        postsList.add(new Post("热门旅行日志", "旅行中的必备物品清单",
                "这篇帖子总结了旅行中必须携带的一些物品，包括衣物、电子设备、药品等，不同的旅行需求会有不同的清单，本文会为你提供一份旅行必备清单...",
                "user2", "旅行时，准备好合适的物品是非常重要的。无论是长途旅行还是短途周末游，准备工作都能让你的旅行更加顺利...",
                Arrays.asList(R.drawable.post_clothes),null, System.currentTimeMillis()));

        postsList.add(new Post("景区推荐", "西湖的美景",
                "西湖是一个美丽的景区，四季皆有不同的风景，非常适合摄影爱好者，尤其是在春秋季节，景色最为迷人...",
                "user3", "西湖不仅仅是一个自然景区，它还是中国文化的象征之一。无论你是徒步游玩还是骑行，西湖都会带给你不同的体验...",
                Arrays.asList(R.drawable.west_lake),null, System.currentTimeMillis()));

        postsList.add(new Post("景区推荐", "张家界的绝美景观",
                "张家界是中国著名的景区，拥有独特的自然风光和丰富的旅游资源，其中的天子山和黄石寨等景点吸引了大量游客...",
                "user4", "张家界的绝美景观将让你目不暇接，它以独特的山脉景观、深邃的峡谷以及丰富的生物资源而闻名。如果你是自然景观爱好者，这里是你的理想去处...",
                Arrays.asList(R.drawable.zhangjiajie),null, System.currentTimeMillis()));

        postsList.add(new Post("美食推荐", "上海的必吃小吃",
                "上海有很多美味的小吃，推荐尝试小笼包、蟹壳黄等地道美食，特别是小笼包，薄皮包馅，汁多味美，是上海的标志性小吃之一...",
                "user1", "上海的小吃文化源远流长，从早到晚，不同的地方都有美食等你品尝。这里的夜市摊位总是人山人海，推荐大家一定要尝尝上海的本地特色小吃...",
                Arrays.asList(R.drawable.zhangjiajie),null, System.currentTimeMillis()));

        postsList.add(new Post("美食推荐", "北京的夜宵推荐",
                "北京的夜宵文化源远流长，这里有许多值得尝试的小吃，例如爆肚、羊肉串等，在深夜依然可以找到热腾腾的小摊...",
                "user2", "北京的夜宵总能给你带来意外的惊喜，尤其是深夜走在胡同巷子里，能吃到各种美味的地道小吃，这些是你无法在其他地方找到的...",
                Arrays.asList(R.drawable.fourseasons_beijing),null, System.currentTimeMillis()));

        postsList.add(new Post("民宿推荐", "推荐一家海边的民宿",
                "这是一家坐落在海边的民宿，非常适合度假，环境非常优美，房间里有巨大的落地窗，早上醒来能看到海浪拍打沙滩的美景...",
                "user3", "如果你热爱海边的景色，这家民宿是你理想的选择。你可以享受阳光沙滩、海风和美丽的海景，还可以在房间内放松身心，享受悠闲时光...",
                Arrays.asList(R.drawable.sofitel_hangzhou), null,System.currentTimeMillis()));

        postsList.add(new Post("民宿推荐", "山间民宿推荐",
                "山间的民宿适合喜欢清静和自然的游客，推荐几家环境非常好的民宿，这些民宿不仅提供舒适的住宿体验，还能享受山间的宁静与美丽...",
                "user4", "对于喜爱大自然的朋友来说，山间民宿是一个很好的选择。在这里你可以远离城市的喧嚣，享受清新空气，放松心情...",
                Arrays.asList(R.drawable.stregis_chengdu),null, System.currentTimeMillis()));
    }


    // 动态加载类别部分
    private void addCategorySection(String categoryTitle, int iconResId, int backgroundColorResId, int textColorResId, int iconTintColorResId) {
        // 加载 item_category_section 布局
        View categoryView = LayoutInflater.from(getContext()).inflate(R.layout.item_category_section, categoriesContainer, false);

        // 设置标题和图标
        ImageView icon = categoryView.findViewById(R.id.icon);
        TextView title = categoryView.findViewById(R.id.category_title);
        RecyclerView recyclerView = categoryView.findViewById(R.id.category_recyclerview);

        icon.setImageResource(iconResId);  // 设置图标
        title.setText(categoryTitle);  // 设置标题

        // 设置动态颜色
        icon.setColorFilter(getResources().getColor(iconTintColorResId));  // 设置图标颜色
        title.setTextColor(getResources().getColor(textColorResId));  // 设置文本颜色
        categoryView.setBackgroundColor(getResources().getColor(backgroundColorResId));  // 设置背景颜色

        // 设置RecyclerView适配器
        PostAdapter adapter = new PostAdapter(filterPostsByCategory(categoryTitle), usersList, post -> {
            // 点击帖子后，跳转到详情页
            Intent intent = new Intent(getActivity(), PostDetailActivity.class);
            intent.putExtra("post", post);  // 传递Post对象
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // 将动态加载的类别布局添加到容器中
        categoriesContainer.addView(categoryView);
    }

    // 根据类别过滤帖子
    private List<Post> filterPostsByCategory(String category) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : postsList) {
            if (post.getCategory().equals(category)) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }
}
