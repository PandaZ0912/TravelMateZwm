package com.example.travelmate.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.activities.PurchaseActivity;
import com.example.travelmate.models.TransportItem;

import java.util.List;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.TransportViewHolder> {

    private List<TransportItem> transportItemList;
    private Context context;  // 声明 context

    // 通过构造函数传递 context
    public TransportAdapter(Context context, List<TransportItem> transportItemList) {
        this.context = context;
        this.transportItemList = transportItemList;
    }

    @Override
    public TransportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transport, parent, false);
        return new TransportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransportViewHolder holder, int position) {
        TransportItem transportItem = transportItemList.get(position);

        // Set data to views
        holder.trainNoTextView.setText(transportItem.getTrainNo());
        holder.departureTimeTextView.setText(transportItem.getDepartureTime());
        holder.arrivalTimeTextView.setText(transportItem.getArrivalTime());
        holder.departureTextView.setText(transportItem.getDeparture());
        holder.arrivalTextView.setText(transportItem.getArrival());
        holder.priceTextView.setText(String.format("¥ %.2f", transportItem.getPrice()));
        holder.seatTypeTextView.setText(transportItem.getSeatType());

        // 设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动购买页面，并传递数据
                Intent intent = new Intent(context, PurchaseActivity.class);
                intent.putExtra("trainName", transportItem.getTrainNo());
                intent.putExtra("departureTime", transportItem.getDepartureTime());
                intent.putExtra("arrivalTime", transportItem.getArrivalTime());
                intent.putExtra("fromStation", transportItem.getDeparture());
                intent.putExtra("toStation", transportItem.getArrival());
                intent.putExtra("price", transportItem.getPrice());
                intent.putExtra("seatType", transportItem.getSeatType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transportItemList.size();
    }

    public static class TransportViewHolder extends RecyclerView.ViewHolder {

        TextView trainNoTextView;
        TextView departureTimeTextView;
        TextView arrivalTimeTextView;
        TextView departureTextView;
        TextView arrivalTextView;
        TextView priceTextView;
        TextView seatTypeTextView;

        public TransportViewHolder(View itemView) {
            super(itemView);
            trainNoTextView = itemView.findViewById(R.id.train_no);
            departureTimeTextView = itemView.findViewById(R.id.departure_time);
            arrivalTimeTextView = itemView.findViewById(R.id.arrival_time);
            departureTextView = itemView.findViewById(R.id.departure);
            arrivalTextView = itemView.findViewById(R.id.arrival);
            priceTextView = itemView.findViewById(R.id.price);
            seatTypeTextView = itemView.findViewById(R.id.seat_type);
        }
    }
}
