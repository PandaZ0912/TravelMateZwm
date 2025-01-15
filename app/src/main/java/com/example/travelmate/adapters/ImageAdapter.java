package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelmate.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<Integer> imageResIds;  // 用来加载图片的资源 ID
    private List<String> imageUrls;  // 用来加载图片的 URL

    // 静态方法来创建适配器（根据资源ID或URL）
    public static ImageAdapter createWithResIds(List<Integer> resIds) {
        ImageAdapter adapter = new ImageAdapter();
        adapter.imageResIds = resIds;
        return adapter;
    }

    public static ImageAdapter createWithUrls(List<String> urls) {
        ImageAdapter adapter = new ImageAdapter();
        adapter.imageUrls = urls;
        return adapter;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建视图并确保其宽高为 match_parent
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        return new ImageViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        // 给视图设置数据，这里简单设置图片资源 ID 或 URL
        if (imageResIds != null && !imageResIds.isEmpty()) {
            holder.imageView.setImageResource(imageResIds.get(position));
        } else if (imageUrls != null && !imageUrls.isEmpty()) {
            // 这里你可以使用库（如 Picasso 或 Glide）来加载图片
            Glide.with(holder.imageView.getContext())
                    .load(imageUrls.get(position))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return imageResIds != null ? imageResIds.size() : imageUrls.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}
