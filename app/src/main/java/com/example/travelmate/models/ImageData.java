package com.example.travelmate.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageData implements Parcelable {
    private int imageResId;
    private ImageSource imageSource;

    public enum ImageSource {
        DRAWABLE, URL
    }

    public ImageData(int imageResId, ImageSource imageSource) {
        this.imageResId = imageResId;
        this.imageSource = imageSource;
    }

    protected ImageData(Parcel in) {
        imageResId = in.readInt();
        imageSource = ImageSource.valueOf(in.readString()); // 读取枚举类型
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeString(imageSource.name()); // 序列化枚举类型
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

    public int getImageResId() {
        return imageResId;
    }

    public ImageSource getImageSource() {
        return imageSource;
    }
}
