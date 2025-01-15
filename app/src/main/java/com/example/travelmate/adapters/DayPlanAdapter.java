package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.DayPlan;
import java.util.List;

public class DayPlanAdapter extends RecyclerView.Adapter<DayPlanAdapter.DayPlanViewHolder> {

    private List<DayPlan> dayPlans;

    public DayPlanAdapter(List<DayPlan> dayPlans) {
        this.dayPlans = dayPlans;
    }

    @NonNull
    @Override
    public DayPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_plan, parent, false);
        return new DayPlanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayPlanViewHolder holder, int position) {
        DayPlan dayPlan = dayPlans.get(position);

        // 设置日期
        holder.tvDate.setText(dayPlan.getDate());

        // 设置当天活动的适配器
        ActivityAdapter activityAdapter = new ActivityAdapter(dayPlan.getActivities());
        holder.rvActivities.setLayoutManager(new LinearLayoutManager(holder.rvActivities.getContext()));
        holder.rvActivities.setAdapter(activityAdapter);
    }

    @Override
    public int getItemCount() {
        return dayPlans.size();
    }

    public static class DayPlanViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        RecyclerView rvActivities;

        public DayPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            rvActivities = itemView.findViewById(R.id.rv_activities);
        }
    }
}
