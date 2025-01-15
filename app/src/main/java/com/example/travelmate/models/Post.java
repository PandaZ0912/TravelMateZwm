package com.example.travelmate.models;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private String category;
    private String title;
    private String summary;
    private String username;
    private String content;
    private List<Integer> imageResId; // 图片资源ID（如果是内置资源）
    private List<String> imageUrls; // 图片的存储路径（如果是外部存储）
    private long timestamp;  // 新增字段，用于存储时间戳

    // 更新构造函数以支持时间戳
    public Post(String category, String title, String summary, String username, String content, List<Integer> imageResId,List<String> imageUrls, long timestamp) {
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.username = username;
        this.content = content;
        this.imageUrls = imageUrls;
        this.imageResId = imageResId;
        this.timestamp = timestamp;  // 初始化时间戳
    }

    // Getter和Setter方法
    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getImageResId() {
        return imageResId;
    }

    public void setImageResId(List<Integer> imageResId) {
        this.imageResId = imageResId;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public long getTimestamp() {
        return timestamp;  // 获取时间戳
    }
}
