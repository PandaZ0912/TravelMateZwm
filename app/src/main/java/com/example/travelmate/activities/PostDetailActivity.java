package com.example.travelmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.travelmate.R;
import com.example.travelmate.models.Post;
import com.example.travelmate.adapters.ImageAdapter;

import java.util.List;

public class PostDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView summaryTextView;
    private TextView usernameTextView;
    private TextView contentTextView;
    private ViewPager2 viewPager;
    private ImageButton backButton;  // 返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        // 初始化视图
        titleTextView = findViewById(R.id.detail_title);
        summaryTextView = findViewById(R.id.detail_summary);
        usernameTextView = findViewById(R.id.detail_username);
        contentTextView = findViewById(R.id.detail_content);
        viewPager = findViewById(R.id.detail_viewpager);
        backButton = findViewById(R.id.back_button);

        // 获取传递的帖子对象
        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("post");

        // 设置数据
        if (post != null) {
            titleTextView.setText(post.getTitle());
            summaryTextView.setText(post.getSummary());
            usernameTextView.setText(post.getUsername());
            contentTextView.setText(post.getContent());

            // 设置图片展示
            List<Integer> imageResIds = post.getImageResId();
            List<String> imageUrls = post.getImageUrls();

            if (imageResIds != null && !imageResIds.isEmpty()) {
                // 使用资源 ID 创建适配器
                ImageAdapter imageAdapter = ImageAdapter.createWithResIds(imageResIds);
                viewPager.setAdapter(imageAdapter);
            } else if (imageUrls != null && !imageUrls.isEmpty()) {
                // 使用 URL 创建适配器
                ImageAdapter imageAdapter = ImageAdapter.createWithUrls(imageUrls);
                viewPager.setAdapter(imageAdapter);
            }

            // 设置返回按钮点击事件
            backButton.setOnClickListener(view -> {
                onBackPressed();  // 调用系统的返回操作
            });
        }
    }
}
