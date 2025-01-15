package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelmate.R;
import com.example.travelmate.models.Order;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private OnOrderClickListener onOrderClickListener;

    // 定义接口
    public interface OnOrderClickListener {
        void onOrderClick(Order order);
    }

    // 构造函数，接收订单列表和监听器
    public OrderAdapter(List<Order> orderList, OnOrderClickListener listener) {
        this.orderList = orderList;
        this.onOrderClickListener = listener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderTitle.setText(order.getTitle());
        holder.orderDate.setText(order.getDate());
        holder.orderPrice.setText(order.getPrice());

        // 为订单项设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (onOrderClickListener != null) {
                onOrderClickListener.onOrderClick(order); // 调用接口方法
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // ViewHolder
    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderTitle, orderDate, orderPrice;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderTitle = itemView.findViewById(R.id.tv_order_title);
            orderDate = itemView.findViewById(R.id.tv_order_date);
            orderPrice = itemView.findViewById(R.id.tv_order_price);
        }
    }
}
