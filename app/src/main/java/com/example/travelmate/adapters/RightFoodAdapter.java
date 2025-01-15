package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.FoodItem;

import java.util.List;

public class RightFoodAdapter extends RecyclerView.Adapter<RightFoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodList;

    public RightFoodAdapter(List<FoodItem> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodDescription.setText(food.getDescription());
        holder.foodPrice.setText(String.format("¥%.2f", food.getPrice()));
        holder.foodRating.setText(String.format("评分：%.1f", food.getRating()));
        holder.foodImage.setImageResource(food.getImageResId());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    // 更新食物列表
    public void updateFoodList(List<FoodItem> newFoodList) {
        foodList.clear();
        foodList.addAll(newFoodList);
        notifyDataSetChanged();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodDescription, foodPrice,foodRating;
        ImageView foodImage;

        public FoodViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodDescription = itemView.findViewById(R.id.food_description);
            foodPrice = itemView.findViewById(R.id.food_price);
            foodRating = itemView.findViewById(R.id.food_rating);
            foodImage = itemView.findViewById(R.id.food_image);
        }
    }
}
