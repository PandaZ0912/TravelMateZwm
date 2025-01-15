package com.example.travelmate.models;

import java.util.List;

public class BoardItem {

    private String title; // 榜单的标题
    private List<RankItem> rankItems; // 榜单下的项

    // 构造函数
    public BoardItem(String title, List<RankItem> rankItems) {
        this.title = title;
        this.rankItems = rankItems;
    }

    // 获取榜单标题
    public String getTitle() {
        return title;
    }

    // 设置榜单标题
    public void setTitle(String title) {
        this.title = title;
    }

    // 获取榜单下的项
    public List<RankItem> getRankItems() {
        return rankItems;
    }

    // 设置榜单下的项
    public void setRankItems(List<RankItem> rankItems) {
        this.rankItems = rankItems;
    }
}

