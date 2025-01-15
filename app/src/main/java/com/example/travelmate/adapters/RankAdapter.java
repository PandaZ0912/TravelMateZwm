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
import com.example.travelmate.models.RankItem;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {

    private List<RankItem> rankItems;

    public RankAdapter(List<RankItem> rankItems) {
        this.rankItems = rankItems;
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        return new RankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        RankItem rankItem = rankItems.get(position);
        holder.nameTextView.setText(rankItem.getTitle());
        holder.descriptionTextView.setText(rankItem.getDescription());
        holder.ratingTextView.setText(String.valueOf(rankItem.getRating()));

        // 设置图片（可以使用 Glide 或 Picasso）
        Glide.with(holder.imageView.getContext())
                .load(rankItem.getImagePath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return rankItems.size();
    }

    public static class RankViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView ratingTextView;
        ImageView imageView;

        public RankViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.rank_name);
            descriptionTextView = itemView.findViewById(R.id.rank_description);
            ratingTextView = itemView.findViewById(R.id.rank_rating);
            imageView = itemView.findViewById(R.id.rank_image);
        }
    }
}
