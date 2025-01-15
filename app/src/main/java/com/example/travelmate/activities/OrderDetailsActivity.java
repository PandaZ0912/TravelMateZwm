package com.example.travelmate.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.models.Order;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView tvOrderTitle, tvOrderDate, tvOrderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        tvOrderTitle = findViewById(R.id.tv_order_title);
        tvOrderDate = findViewById(R.id.tv_order_date);
        tvOrderPrice = findViewById(R.id.tv_order_price);

        // 获取 Intent 中传递的 Order 数据
        Order order = (Order) getIntent().getSerializableExtra("ORDER_DATA");

        if (order != null) {
            tvOrderTitle.setText(order.getTitle());
            tvOrderDate.setText(order.getDate());
            tvOrderPrice.setText(order.getPrice());
        }
    }
}

