package com.example.travelmate.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class Activity implements Parcelable {
    private String time;
    private String title;
    private String place;
    private String description;
    private List<ImageData> images;

    public enum ImageSource {
        DRAWABLE, URL
    }

    public Activity(String time, String title, String place, String description, List<ImageData> images) {
        this.time = time;
        this.title = title;
        this.place = place;
        this.description = description;
        this.images = images;
    }

    protected Activity(Parcel in) {
        time = in.readString();
        title = in.readString();
        place = in.readString();
        description = in.readString();
        images = in.createTypedArrayList(ImageData.CREATOR); // 反序列化图片列表
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(title);
        dest.writeString(place);
        dest.writeString(description);
        dest.writeTypedList(images); // 写入图片列表
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Activity> CREATOR = new Creator<Activity>() {
        @Override
        public Activity createFromParcel(Parcel in) {
            return new Activity(in);
        }

        @Override
        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public List<ImageData> getImages() {
        return images;
    }
}
