package com.example.travelmate.models;

public class ModuleModel {
    private String name;
    private String description;
    private int iconResId;
    private String targetActivity;

    // 构造函数
    public ModuleModel(String name, String description, int iconResId, String targetActivity) {
        this.name = name;
        this.description = description;
        this.iconResId = iconResId;
        this.targetActivity = targetActivity;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getTargetActivity() {
        return targetActivity;
    }
}
