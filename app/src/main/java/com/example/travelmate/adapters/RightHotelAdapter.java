package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.HotelItem;

import java.util.List;

public class RightHotelAdapter extends RecyclerView.Adapter<RightHotelAdapter.HotelViewHolder> {

    private List<HotelItem> hotelList;

    public RightHotelAdapter(List<HotelItem> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        HotelItem hotel = hotelList.get(position);
        holder.hotelName.setText(hotel.getName());
        holder.hotelDescription.setText(hotel.getDescription());
        holder.hotelImage.setImageResource(hotel.getImageResId());
        holder.hotelRating.setText(String.format("Rating: %.1f", hotel.getRating()));
        holder.hotelPrice.setText(String.format("Price: ¥%d", hotel.getPrice()));
        holder.hotelAddress.setText(String.format("Address: %s", hotel.getAddress()));
        holder.hotelType.setText(String.format("Type: %s", hotel.getHotelType()));
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public void updateHotels(List<HotelItem> newHotelList) {
        hotelList.clear();
        hotelList.addAll(newHotelList);
        notifyDataSetChanged();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView hotelName, hotelDescription, hotelRating, hotelPrice, hotelAddress, hotelType;
        ImageView hotelImage;

        public HotelViewHolder(View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelDescription = itemView.findViewById(R.id.hotel_description);
            hotelImage = itemView.findViewById(R.id.hotel_image);
            hotelRating = itemView.findViewById(R.id.hotel_rating);
            hotelPrice = itemView.findViewById(R.id.hotel_price);
            hotelAddress = itemView.findViewById(R.id.hotel_address);
            hotelType = itemView.findViewById(R.id.hotel_type);
        }
    }
}
