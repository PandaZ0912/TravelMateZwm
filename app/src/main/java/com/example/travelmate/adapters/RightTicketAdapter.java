package com.example.travelmate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.TicketItem;

import java.util.List;

public class RightTicketAdapter extends RecyclerView.Adapter<RightTicketAdapter.TicketViewHolder> {

    private List<TicketItem> ticketList;

    public RightTicketAdapter(List<TicketItem> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        TicketItem ticket = ticketList.get(position);
        holder.ticketName.setText(ticket.getName());
        holder.ticketDescription.setText(ticket.getDescription());
        holder.ticketPrice.setText("¥" + ticket.getPrice());
        holder.ticketRating.setRating(ticket.getRating());
        holder.ticketImage.setImageResource(ticket.getImageResId());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView ticketName, ticketDescription, ticketPrice;
        RatingBar ticketRating;
        ImageView ticketImage;

        public TicketViewHolder(View itemView) {
            super(itemView);
            ticketName = itemView.findViewById(R.id.ticket_name);
            ticketDescription = itemView.findViewById(R.id.ticket_description);
            ticketPrice = itemView.findViewById(R.id.ticket_price);
            ticketRating = itemView.findViewById(R.id.ticket_rating);
            ticketImage = itemView.findViewById(R.id.ticket_image);
        }
    }
}
