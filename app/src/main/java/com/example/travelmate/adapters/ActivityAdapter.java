package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelmate.R;
import com.example.travelmate.models.Activity;
import com.example.travelmate.models.ImageData;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    private List<Activity> activities;

    public ActivityAdapter(List<Activity> activities) {
        this.activities = activities != null ? activities : new ArrayList<>();
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载布局
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = activities.get(position);

        // 设置文本内容
        holder.tvTime.setText(activity.getTime());
        holder.tvTitle.setText(activity.getTitle());
        holder.tvLocation.setText(activity.getPlace());
        holder.tvDescription.setText(activity.getDescription());

        // 设置图片（如果有图片）
        List<ImageData> images = activity.getImages();
        if (images != null && !images.isEmpty()) {
            ImageData imageData = images.get(0); // 假设选择第一个图片
            switch (imageData.getImageSource()) {
                case DRAWABLE:
                    holder.ivActivityImage.setImageResource(imageData.getImageResId());
                    break;
                case URL:
                    // 使用 Glide 或其他图片加载库加载网络图片
                    Glide.with(holder.ivActivityImage.getContext())
                            .load(imageData.getImageResId())  // 这可以是 URL 或文件路径
                            .into(holder.ivActivityImage);
                    break;
            }
        } else {
            holder.ivActivityImage.setVisibility(View.GONE); // 如果没有图片，隐藏 ImageView
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvTitle, tvLocation, tvDescription;
        ImageView ivActivityImage; // 引用 ImageView

        public ActivityViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_activity_time);
            tvTitle = itemView.findViewById(R.id.tv_activity_title);
            tvLocation = itemView.findViewById(R.id.tv_activity_place);
            tvDescription = itemView.findViewById(R.id.tv_activity_description);
            ivActivityImage = itemView.findViewById(R.id.iv_activity_image); // 正确引用
        }
    }
}

