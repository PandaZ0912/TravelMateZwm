package com.example.travelmate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.travelmate.R;
import com.example.travelmate.models.TravelLog;

import java.util.List;

public class TravelLogAdapter extends ArrayAdapter<TravelLog> {

    public TravelLogAdapter(Context context, List<TravelLog> logs) {
        super(context, 0, logs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_travel_log, parent, false);
        }

        TravelLog log = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.log_title);
        titleTextView.setText(log.getTitle());

        TextView dateTextView = convertView.findViewById(R.id.log_date);
        dateTextView.setText(log.getDate());

        return convertView;
    }
}
