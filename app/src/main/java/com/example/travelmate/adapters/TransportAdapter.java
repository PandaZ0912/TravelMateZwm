package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.TransportItem;

import java.util.List;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.TransportViewHolder> {

    private List<TransportItem> transportList;

    public TransportAdapter(List<TransportItem> transportList) {
        this.transportList = transportList;
    }

    @NonNull
    @Override
    public TransportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transport, parent, false);
        return new TransportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransportViewHolder holder, int position) {
        TransportItem transport = transportList.get(position);
        holder.trainName.setText(transport.getTrainName());
        holder.departureTime.setText("出发时间: " + transport.getDepartureTime());
        holder.arrivalTime.setText("到达时间: " + transport.getArrivalTime());
        holder.route.setText(transport.getFromStation() + " - " + transport.getToStation());
        holder.price.setText("¥" + transport.getPrice());
        holder.seatType.setText(transport.getSeatType());
    }

    @Override
    public int getItemCount() {
        return transportList.size();
    }

    public static class TransportViewHolder extends RecyclerView.ViewHolder {
        TextView trainName, departureTime, arrivalTime, route, price, seatType;

        public TransportViewHolder(View itemView) {
            super(itemView);
            trainName = itemView.findViewById(R.id.transport_train_name);
            departureTime = itemView.findViewById(R.id.transport_departure_time);
            arrivalTime = itemView.findViewById(R.id.transport_arrival_time);
            route = itemView.findViewById(R.id.transport_route);
            price = itemView.findViewById(R.id.transport_price);
            seatType = itemView.findViewById(R.id.transport_seat_type);
        }
    }
}
