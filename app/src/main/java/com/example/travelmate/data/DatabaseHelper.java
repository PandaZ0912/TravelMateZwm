package com.example.travelmate.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.travelmate.R;
import com.example.travelmate.TravelMateApplication;
import com.example.travelmate.models.BoardItem;
import com.example.travelmate.models.RankItem;
import com.example.travelmate.models.TravelLog;
import com.example.travelmate.models.User;
import com.example.travelmate.utils.EncryptionUtils;
import com.example.travelmate.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context = TravelMateApplication.getAppContext();

    // 创建用户表的SQL语句，包含手机号和邮箱
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + DatabaseConstants.TABLE_USERS + "("
            + DatabaseConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DatabaseConstants.COLUMN_USERNAME + " TEXT UNIQUE,"
            + DatabaseConstants.COLUMN_PHONE + " TEXT UNIQUE,"
            + DatabaseConstants.COLUMN_EMAIL + " TEXT UNIQUE,"
            + DatabaseConstants.COLUMN_PASSWORD + " TEXT,"
            + DatabaseConstants.COLUMN_SALT + " TEXT"
            + ")";

    // 创建 BoardItems 表
    private static final String CREATE_TABLE_BOARD = "CREATE TABLE " + DatabaseConstants.TABLE_BOARD_ITEMS + " ("
            + DatabaseConstants.BOARD_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseConstants.BOARD_ITEM_TITLE + " TEXT NOT NULL)";

    // 创建 RankItems 表
    private static final String CREATE_TABLE_RANK = "CREATE TABLE " + DatabaseConstants.TABLE_RANK_ITEMS + " ("
            + DatabaseConstants.RANK_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseConstants.RANK_ITEM_BOARD_ID + " INTEGER NOT NULL, "
            + DatabaseConstants.RANK_ITEM_TITLE + " TEXT NOT NULL, "
            + DatabaseConstants.RANK_ITEM_DESCRIPTION + " TEXT NOT NULL, "
            + DatabaseConstants.RANK_ITEM_RATING + " REAL NOT NULL, "
            + DatabaseConstants.RANK_ITEM_IMAGE_PATH + " TEXT)";

    // 创建旅行日志表的SQL语句
    private static final String CREATE_TABLE_TRAVEL_LOGS = "CREATE TABLE " + DatabaseConstants.TABLE_TRAVEL_LOGS + " ("
            + DatabaseConstants.COLUMN_LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseConstants.COLUMN_LOG_TITLE + " TEXT, "
            + DatabaseConstants.COLUMN_LOG_CONTENT + " TEXT, "
            + DatabaseConstants.COLUMN_LOG_LOCATION + " TEXT, "
            + DatabaseConstants.COLUMN_LOG_DATE + " TEXT, "
            + DatabaseConstants.COLUMN_LOG_IMAGE_PATH + " TEXT, "
            + DatabaseConstants.COLUMN_USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + DatabaseConstants.COLUMN_USER_ID + ") REFERENCES " + DatabaseConstants.TABLE_USERS + "(" + DatabaseConstants.COLUMN_ID + ")"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_BOARD);
        db.execSQL(CREATE_TABLE_RANK);
        db.execSQL(CREATE_TABLE_TRAVEL_LOGS);
        // insertData(db, context, boardItemList);
        // 在数据库创建时初始化数据
        initializeData(db, context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS BoardItems");
        db.execSQL("DROP TABLE IF EXISTS RankItems");
        db.execSQL("DROP TABLE IF EXISTS TravelLogs");
        onCreate(db);
    }

    // 注册用户方法，包括用户名、手机号、邮箱、密码、盐值
    public boolean registerUser(String username, String phone, String email, String password, String salt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.COLUMN_USERNAME, username);
        values.put(DatabaseConstants.COLUMN_PHONE, phone);
        values.put(DatabaseConstants.COLUMN_EMAIL, email);
        values.put(DatabaseConstants.COLUMN_PASSWORD, password);
        values.put(DatabaseConstants.COLUMN_SALT, salt);

        long result = db.insert(DatabaseConstants.TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    // 登录用户方法，验证用户名和密码
    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexPassword = cursor.getColumnIndex(DatabaseConstants.COLUMN_PASSWORD);
            int columnIndexSalt = cursor.getColumnIndex(DatabaseConstants.COLUMN_SALT);

            if (columnIndexPassword == -1 || columnIndexSalt == -1) {
                Log.e("DatabaseHelper", "Column not found in cursor");
                cursor.close();
                return false;
            }

            String storedEncryptedPassword = cursor.getString(columnIndexPassword);
            String storedSalt = cursor.getString(columnIndexSalt);

            try {
                // 解密存储的密码
                String decryptedPassword = EncryptionUtils.decrypt(storedEncryptedPassword, storedSalt);
                cursor.close();
                return decryptedPassword.equals(password);
            } catch (Exception e) {
                e.printStackTrace();
                cursor.close();
                return false;
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return false;
    }

    public User getUserDetailsByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        // 查询用户表
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        // 如果查询结果存在且成功移动到第一行
        if (cursor != null && cursor.moveToFirst()) {
            // 获取各列的索引
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.COLUMN_ID));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_PHONE));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_EMAIL));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_PASSWORD));
            @SuppressLint("Range") String salt = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_SALT));

            // 创建 User 对象
            user = new User(id, username, phone, email, password, salt);

            cursor.close();  // 关闭 cursor
        }

        db.close();  // 关闭数据库连接
        return user;
    }


    // 根据邮箱或手机号更新用户密码
    public boolean updatePassword(String emailOrPhone, String newPassword) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();

        // 使用SQL查询查找用户
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_USERNAME + " = ? OR " + DatabaseConstants.COLUMN_PHONE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{emailOrPhone, emailOrPhone});

        if (cursor != null && cursor.moveToFirst()) {
            // 获取盐值
            @SuppressLint("Range") String salt = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_SALT));

            // 使用盐值加密新密码
            String encryptedPassword = EncryptionUtils.encrypt(newPassword, salt);

            // 更新密码
            ContentValues values = new ContentValues();
            values.put(DatabaseConstants.COLUMN_PASSWORD, encryptedPassword);

            int rowsUpdated = db.update(DatabaseConstants.TABLE_USERS, values, DatabaseConstants.COLUMN_USERNAME + " = ? OR " + DatabaseConstants.COLUMN_PHONE + " = ?",
                    new String[]{emailOrPhone, emailOrPhone});
            cursor.close();
            db.close();
            return rowsUpdated > 0;  // 返回更新结果
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return false; // 用户未找到，返回false
    }

    // 校验用户名是否已存在
    public boolean doesUserExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // 校验手机号是否已存在
    public boolean doesPhoneExist(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_PHONE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{phone});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // 校验邮箱是否已存在
    public boolean doesEmailExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void initializeData(SQLiteDatabase db, Context context) {
        // 检查是否已经有数据
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM BoardItems", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count > 0) {
            // 如果已经有数据，不再插入
            return;
        }

        db.beginTransaction(); // 开启事务
        try {
            // 初始化数据列表
            List<BoardItem> boardItemList = Arrays.asList(
                    new BoardItem("景区必玩榜", Arrays.asList(
                            new RankItem(1, "长城", "中国古代建筑奇迹，蜿蜒万里，气势恢宏", 4.9, R.drawable.great_wall, null),
                            new RankItem(2, "故宫", "中国明清皇宫，世界文化遗产", 5.0, R.drawable.forbidden_city, null),
                            new RankItem(3, "桂林山水", "甲天下的自然景观，享受山水之美", 4.8, R.drawable.guilin, null),
                            new RankItem(4, "张家界国家森林公园", "绝美的奇峰异石，灵动的自然景色", 4.7, R.drawable.zhangjiajie, null),
                            new RankItem(5, "九寨沟", "五彩斑斓的湖泊，宛如仙境的自然保护区", 4.9, R.drawable.jiuzhaigou, null)
                    )),
                    new BoardItem("酒店必住榜", Arrays.asList(
                            new RankItem(6, "北京四季酒店", "豪华舒适，顶级服务，地理位置优越", 4.9, R.drawable.fourseasons_beijing, null),
                            new RankItem(7, "上海外滩茂悦大酒店", "享外滩美景，极致奢华体验", 4.8, R.drawable.hyatt_bund_shanghai, null),
                            new RankItem(8, "杭州西湖索菲特大酒店", "湖畔酒店，静谧优雅，服务一流", 4.7, R.drawable.sofitel_hangzhou, null),
                            new RankItem(9, "成都瑞吉酒店", "现代与传统的完美融合，四川文化体验", 4.9, R.drawable.stregis_chengdu, null),
                            new RankItem(10, "三亚亚特兰蒂斯酒店", "海景度假首选，亲子乐园的天堂", 4.8, R.drawable.atlantis_sanya, null)
                    )),
                    new BoardItem("美食必吃榜", Arrays.asList(
                            new RankItem(11, "北京烤鸭", "外酥里嫩，地道北京味", 5.0, R.drawable.peking_duck, null),
                            new RankItem(12, "重庆火锅", "麻辣鲜香，正宗川渝风味", 4.9, R.drawable.chongqing_hotpot, null),
                            new RankItem(13, "广州早茶", "茶点佳肴，传统与现代的结合", 4.8, R.drawable.cantonese_dim_sum, null),
                            new RankItem(14, "西安肉夹馍", "皮脆馅嫩，秦味特色小吃", 4.8, R.drawable.roujiamo, null),
                            new RankItem(15, "云南过桥米线", "鲜美汤底，软滑米线，味道独特", 4.7, R.drawable.cross_bridge_rice_noodles, null)
                    ))
            );

            // 插入数据
            for (BoardItem boardItem : boardItemList) {
                ContentValues boardValues = new ContentValues();
                boardValues.put("title", boardItem.getTitle());
                long boardId = db.insert(DatabaseConstants.TABLE_BOARD_ITEMS, null, boardValues);

                for (RankItem rankItem : boardItem.getRankItems()) {
                    String imagePath = null;
                    if (rankItem.getImageResId() != 0) {
                        try {
                            // Context appContext = context.getApplicationContext();
                            imagePath = ImageUtils.saveImageToStorage(context, rankItem.getImageResId(), rankItem.getId() + ".png");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    ContentValues rankValues = new ContentValues();
                    rankValues.put("board_id", boardId);
                    rankValues.put("title", rankItem.getTitle());
                    rankValues.put("description", rankItem.getDescription());
                    rankValues.put("rating", rankItem.getRating());
                    rankValues.put("image_path", imagePath);
                    db.insert(DatabaseConstants.TABLE_RANK_ITEMS, null, rankValues);
                }
            }

            db.setTransactionSuccessful(); // 提交事务
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // 结束事务
        }
    }

    public List<BoardItem> getAllBoardItems(Context context) {
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        List<BoardItem> boardItemList = new ArrayList<>();

        // 查询所有榜单数据
        Cursor boardCursor = db.query(DatabaseConstants.TABLE_BOARD_ITEMS,
                null, null, null, null, null, null);

        if (boardCursor != null) {
            while (boardCursor.moveToNext()) {
                @SuppressLint("Range") String title = boardCursor.getString(boardCursor.getColumnIndex(DatabaseConstants.BOARD_ITEM_TITLE));

                // 获取当前榜单下的所有项数据
                @SuppressLint("Range") Cursor rankCursor = db.query(DatabaseConstants.TABLE_RANK_ITEMS,
                        null,
                        DatabaseConstants.RANK_ITEM_BOARD_ID + " = ?",
                        new String[]{String.valueOf(boardCursor.getInt(boardCursor.getColumnIndex(DatabaseConstants.BOARD_ITEM_ID)))},
                        null, null, null);

                List<RankItem> rankItems = new ArrayList<>();
                while (rankCursor != null && rankCursor.moveToNext()) {
                    @SuppressLint("Range") Integer id = rankCursor.getInt(rankCursor.getColumnIndex(DatabaseConstants.RANK_ITEM_ID));
                    @SuppressLint("Range") String rankTitle = rankCursor.getString(rankCursor.getColumnIndex(DatabaseConstants.RANK_ITEM_TITLE));
                    @SuppressLint("Range") String description = rankCursor.getString(rankCursor.getColumnIndex(DatabaseConstants.RANK_ITEM_DESCRIPTION));
                    @SuppressLint("Range") double rating = rankCursor.getDouble(rankCursor.getColumnIndex(DatabaseConstants.RANK_ITEM_RATING));
                    @SuppressLint("Range") String imagePath = rankCursor.getString(rankCursor.getColumnIndex(DatabaseConstants.RANK_ITEM_IMAGE_PATH));

                    rankItems.add(new RankItem(id,rankTitle, description, rating, null, imagePath));
                }

                boardItemList.add(new BoardItem(title, rankItems));
            }
        }

        db.close();
        return boardItemList;
    }

    // 添加旅行日志
    public boolean insertTravelLog(int userId, String title, String content, String location, String date, String imagePath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("title", title);
        values.put("content", content);
        values.put("location", location);
        values.put("date", date);
        values.put("image_path", imagePath);

        long result = db.insert("TravelLogs", null, values);
        db.close();
        return result != -1;
    }

    // 更新旅行日志
    public boolean updateTravelLog(int logId, String title, String content, String location, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        values.put("location", location);
        values.put("date", date);

        int rowsUpdated = db.update("TravelLogs", values, "id = ?", new String[]{String.valueOf(logId)});
        db.close();
        return rowsUpdated > 0;
    }

    // 获取所有旅行日志
    public List<TravelLog> getAllTravelLogs(int userId) {
        List<TravelLog> travelLogs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TravelLogs", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            @SuppressLint("Range") TravelLog log = new TravelLog(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("location")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getString(cursor.getColumnIndex("image_path"))
            );
            travelLogs.add(log);
        }
        cursor.close();
        db.close();
        return travelLogs;
    }

    // 根据 ID 获取单条日志
    @SuppressLint("Range")
    public TravelLog getTravelLogById(int logId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TravelLogs", null, "id = ?", new String[]{String.valueOf(logId)}, null, null, null);
        TravelLog log = null;
        if (cursor != null && cursor.moveToFirst()) {
            log = new TravelLog(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("location")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getString(cursor.getColumnIndex("image_path"))
            );
            cursor.close();
        }
        db.close();
        return log;
    }

    // 删除旅行日志
    public boolean deleteTravelLog(int logId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("TravelLogs", "id = ?", new String[]{String.valueOf(logId)});
        db.close();
        return rowsDeleted > 0;
    }

}
