package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelmate.R;
import com.example.travelmate.models.Travel;

import java.util.List;

public class TravelListAdapter extends RecyclerView.Adapter<TravelListAdapter.TravelViewHolder> {

    private List<Travel> travelList;
    private OnItemClickListener onItemClickListener;

    // 定义一个接口，用于处理点击事件
    public interface OnItemClickListener {
        void onItemClick(Travel travel);
    }

    public TravelListAdapter(List<Travel> travelList, OnItemClickListener listener) {
        this.travelList = travelList;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel_card, parent, false);
        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        Travel travel = travelList.get(position);

        holder.tvTripTitle.setText(travel.getDestination());
        holder.tvTripDate.setText(travel.getDateRange());
        holder.ivTripImage.setImageResource(travel.getImageResId());

        // 设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(travel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    public static class TravelViewHolder extends RecyclerView.ViewHolder {

        TextView tvTripTitle, tvTripDate;
        ImageView ivTripImage;

        public TravelViewHolder(View itemView) {
            super(itemView);
            ivTripImage = itemView.findViewById(R.id.iv_trip_image);
            tvTripTitle = itemView.findViewById(R.id.tv_trip_title);
            tvTripDate = itemView.findViewById(R.id.tv_trip_date);
        }
    }
}
