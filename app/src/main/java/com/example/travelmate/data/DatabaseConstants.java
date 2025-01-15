package com.example.travelmate.data;

public class DatabaseConstants {
    public static final String DATABASE_NAME = "TravelMate.db";
    public static final int DATABASE_VERSION = 24;  // 更新版本

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_SALT = "salt";

    // BoardItems 表字段
    public static final String TABLE_BOARD_ITEMS = "BoardItems";
    public static final String BOARD_ITEM_ID = "id";
    public static final String BOARD_ITEM_TITLE = "title";

    // RankItems 表字段
    public static final String TABLE_RANK_ITEMS = "RankItems";
    public static final String RANK_ITEM_ID = "id";
    public static final String RANK_ITEM_BOARD_ID = "board_id";
    public static final String RANK_ITEM_TITLE = "title";
    public static final String RANK_ITEM_DESCRIPTION = "description";
    public static final String RANK_ITEM_RATING = "rating";
    public static final String RANK_ITEM_IMAGE_PATH = "image_path";

    // 旅行日志表和列
    public static final String TABLE_TRAVEL_LOGS = "TravelLogs";
    public static final String COLUMN_LOG_ID = "id";
    public static final String COLUMN_LOG_TITLE = "title";
    public static final String COLUMN_LOG_CONTENT = "content";
    public static final String COLUMN_LOG_LOCATION = "location";
    public static final String COLUMN_LOG_DATE = "date";
    public static final String COLUMN_LOG_IMAGE_PATH = "image_path";
    public static final String COLUMN_USER_ID = "user_id";

}
