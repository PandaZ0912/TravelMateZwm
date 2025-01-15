package com.example.travelmate.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class DayPlan implements Parcelable {
    private String date;
    private List<Activity> activities;

    public DayPlan(String date, List<Activity> activities) {
        this.date = date;
        this.activities = activities;
    }

    protected DayPlan(Parcel in) {
        date = in.readString();
        activities = in.createTypedArrayList(Activity.CREATOR); // 反序列化活动列表
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(activities); // 写入活动列表
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DayPlan> CREATOR = new Creator<DayPlan>() {
        @Override
        public DayPlan createFromParcel(Parcel in) {
            return new DayPlan(in);
        }

        @Override
        public DayPlan[] newArray(int size) {
            return new DayPlan[size];
        }
    };

    public String getDate() {
        return date;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
