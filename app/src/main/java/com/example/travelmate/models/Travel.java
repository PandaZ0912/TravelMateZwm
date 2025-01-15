package com.example.travelmate.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Travel implements Parcelable {
    private String destination;
    private String dateRange;
    private int imageResId;
    private List<DayPlan> dayPlans;

    public Travel(String destination, String dateRange, int imageResId, List<DayPlan> dayPlans) {
        this.destination = destination;
        this.dateRange = dateRange;
        this.imageResId = imageResId;
        this.dayPlans = dayPlans;
    }

    protected Travel(Parcel in) {
        destination = in.readString();
        dateRange = in.readString();
        imageResId = in.readInt();
        dayPlans = in.createTypedArrayList(DayPlan.CREATOR); // 注意要正确反序列化
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destination);
        dest.writeString(dateRange);
        dest.writeInt(imageResId);
        dest.writeTypedList(dayPlans); // 写入 List<DayPlan>
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };

    public String getDestination() {
        return destination;
    }

    public String getDateRange() {
        return dateRange;
    }

    public int getImageResId() {
        return imageResId;
    }

    public List<DayPlan> getDayPlans() {
        return dayPlans;
    }
}
