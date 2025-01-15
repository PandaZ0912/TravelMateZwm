package com.example.travelmate.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;

public class PurchaseActivity extends AppCompatActivity {

    private TextView trainName, departureTime, arrivalTime, fromStation, toStation, seatType, price, totalPrice;
    private EditText quantityEditText;
    private Button purchaseButton;

    private double ticketPrice;  // 单价
    private int quantity = 1;    // 默认数量为1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // 获取视图组件
        trainName = findViewById(R.id.train_name);
        departureTime = findViewById(R.id.departure_time);
        arrivalTime = findViewById(R.id.arrival_time);
        fromStation = findViewById(R.id.from_station);
        toStation = findViewById(R.id.to_station);
        seatType = findViewById(R.id.seat_type);
        price = findViewById(R.id.price);
        totalPrice = findViewById(R.id.total_price);
        quantityEditText = findViewById(R.id.quantity_edittext);
        purchaseButton = findViewById(R.id.purchase_button);

        // 从Intent中获取传递过来的数据
        String train = getIntent().getStringExtra("trainName");
        String depTime = getIntent().getStringExtra("departureTime");
        String arrTime = getIntent().getStringExtra("arrivalTime");
        String from = getIntent().getStringExtra("fromStation");
        String to = getIntent().getStringExtra("toStation");
        ticketPrice = getIntent().getDoubleExtra("price", 0.0);  // 获取票价，确保是double类型
        String seat = getIntent().getStringExtra("seatType");

        // 设置获取的数据到TextView中
        trainName.setText(train);
        departureTime.setText(depTime);
        arrivalTime.setText(arrTime);
        fromStation.setText(from);
        toStation.setText(to);
        seatType.setText(seat);
        price.setText("¥ " + String.format("%.2f", ticketPrice));  // 显示票价，格式化为两位小数

        // 初始化数量输入框
        quantityEditText.setText(String.valueOf(quantity));
        quantityEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                updateQuantityAndTotal();  // 聚焦失去时更新数量和总价
            }
        });

        // 添加文本变化监听器以实时更新总价
        quantityEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // 在文本变化前不做任何操作
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // 在文本变化时实时更新数量和总价
                updateQuantityAndTotal();
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {
                // 在文本改变后不做任何操作
            }
        });

        // 设置购买按钮的点击事件
        purchaseButton.setOnClickListener(v -> {
            try {
                quantity = Integer.parseInt(quantityEditText.getText().toString());
                if (quantity <= 0) {
                    Toast.makeText(PurchaseActivity.this, "请输入有效的数量", Toast.LENGTH_SHORT).show();
                } else {
                    // 在此处可以处理实际的购买操作，例如保存订单、支付等
                    Toast.makeText(PurchaseActivity.this, "购买成功！", Toast.LENGTH_SHORT).show();
                    finish();  // 购买成功后，关闭当前页面并返回
                }
            } catch (NumberFormatException e) {
                Toast.makeText(PurchaseActivity.this, "请输入有效的数量", Toast.LENGTH_SHORT).show();
            }
        });

        // 初始化总价
        updateTotalPrice();
    }

    // 更新数量并计算总价
    private void updateQuantityAndTotal() {
        try {
            quantity = Integer.parseInt(quantityEditText.getText().toString());
        } catch (NumberFormatException e) {
            quantity = 1;  // 如果输入无效，默认为1
        }
        updateTotalPrice();  // 更新总价
    }

    // 更新总价
    private void updateTotalPrice() {
        double total = ticketPrice * quantity;
        totalPrice.setText("总价: ¥ " + String.format("%.2f", total));  // 显示总价，格式化为两位小数
    }
}
