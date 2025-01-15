package com.example.travelmate.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ImageUtils {

    private static final String IMAGE_DIR_NAME = "TravelMateImages";
    private static final int COMPRESSION_QUALITY = 80; // 使用80%的质量进行压缩

    // 将图片保存到设备存储的某个文件夹中，返回图片路径
    public static String saveImageToStorage(Context context, int imageResId, String imageName) throws IOException {
        // 使用 CompletableFuture 异步执行图片保存操作
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                return doSaveImageToStorage(context, imageResId, imageName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 等待任务完成并获取结果，超时则抛出异常
        try {
            return future.get(); // 这里会阻塞直到任务完成
        } catch (Exception e) {
            if (e.getCause() instanceof IOException) {
                throw (IOException) e.getCause();
            } else {
                throw new IOException("Failed to save image", e);
            }
        }
    }

    // 实际执行图片保存的私有方法
    private static String doSaveImageToStorage(Context context, int imageResId, String imageName) throws IOException {
        File directory = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), IMAGE_DIR_NAME);

        // 如果文件夹不存在则创建
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Failed to create directory");
        }

        // 创建图片文件
        File imageFile = new File(directory, imageName);

        // 获取Bitmap图片并尝试压缩
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageResId);
        if (bitmap == null) {
            throw new IOException("Failed to decode resource");
        }

        // 保存图片到文件
        try (FileOutputStream outputStream = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESSION_QUALITY, outputStream);
            outputStream.flush();
        }

        return imageFile.getAbsolutePath(); // 返回图片的路径
    }
}
