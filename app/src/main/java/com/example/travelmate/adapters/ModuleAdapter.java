package com.example.travelmate.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.ModuleModel;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private List<ModuleModel> moduleList;

    // 构造函数
    public ModuleAdapter(List<ModuleModel> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 创建每个模块项的视图
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_module_show, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        // 获取当前模块数据并绑定到视图
        ModuleModel module = moduleList.get(position);
        holder.title.setText(module.getName());
        holder.description.setText(module.getDescription());
        holder.icon.setImageResource(module.getIconResId());

        // 设置边框颜色和标题颜色
        switch (position) {
            case 0:  // 酒店民宿
                setModuleStyle(holder, "#2196F3", "#2196F3");  // 蓝色边框和蓝色标题
                break;
            case 1:  // 交通票务
                setModuleStyle(holder, "#4CAF50", "#4CAF50");  // 绿色边框和绿色标题
                break;
            case 2:  // 景区门票
                setModuleStyle(holder, "#FF9800", "#FF9800");  // 黄色边框和黄色标题
                break;
            case 3:  // 美食在线
                setModuleStyle(holder, "#F48FB1", "#F48FB1");  // 粉色边框和粉色标题
                break;
            case 4:  // 旅行日志
                setModuleStyle(holder, "#81C784", "#81C784");  // 绿色边框和绿色标题
                break;
        }

        // 设置点击事件
        holder.itemView.setOnClickListener(v -> {
            // 获取模块对应的目标 Activity
            String targetActivity = module.getTargetActivity();

            // 如果目标 Activity 不为空，跳转到该 Activity
            if (targetActivity != null && !targetActivity.isEmpty()) {
                try {
                    // 使用反射创建目标 Activity 的 Intent
                    Intent intent = new Intent(holder.itemView.getContext(), Class.forName(targetActivity));
                    holder.itemView.getContext().startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 自定义方法：设置模块的样式（背景色、边框色、标题色）
    private void setModuleStyle(ModuleViewHolder holder, String borderColor, String titleColor) {
        // 设置模块的背景为白色，边框颜色为传入的颜色
        GradientDrawable gradient = new GradientDrawable();
        gradient.setShape(GradientDrawable.RECTANGLE);
        gradient.setCornerRadius(16);  // 设置圆角
        gradient.setStroke(4, Color.parseColor(borderColor));  // 设置边框颜色和宽度
        holder.itemView.setBackground(gradient);  // 设置渐变背景

        // 设置标题颜色
        holder.title.setTextColor(Color.parseColor(titleColor));
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    // 自定义 ViewHolder
    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView icon;

        public ModuleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.module_title);
            description = itemView.findViewById(R.id.module_description);
            icon = itemView.findViewById(R.id.module_icon);
        }
    }
}
