package com.example.travelmate.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelmate.R;
import com.example.travelmate.models.BoardItem;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private List<BoardItem> boardItemList;
    private Context context;

    public BoardAdapter(Context context, List<BoardItem> boardItemList) {
        this.context = context;
        this.boardItemList = boardItemList;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_board, parent, false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        BoardItem boardItem = boardItemList.get(position);
        holder.titleTextView.setText(boardItem.getTitle());

        if (boardItem.getTitle().contains("景区")) {
            holder.rankRecyclerView.setBackgroundColor(Color.parseColor("#C4D6B0"));  // 浅绿色背景
            holder.rankBoard.setBackgroundColor(Color.parseColor("#4A7C47"));  // 深绿色背景
        } else if (boardItem.getTitle().contains("酒店")) {
            holder.rankRecyclerView.setBackgroundColor(Color.parseColor("#B9AEDC"));  // 浅紫色背景
            holder.rankBoard.setBackgroundColor(Color.parseColor("#6A4E92"));  // 深紫色背景
        } else if (boardItem.getTitle().contains("美食")) {
            holder.rankRecyclerView.setBackgroundColor(Color.parseColor("#F5B7B1"));  // 浅红色背景
            holder.rankBoard.setBackgroundColor(Color.parseColor("#9E2A2F"));  // 深红色背景
        }

        // 设置子项的适配器
        RankAdapter rankAdapter = new RankAdapter(boardItem.getRankItems());
        holder.rankRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.rankRecyclerView.setAdapter(rankAdapter);
    }

    @Override
    public int getItemCount() {
        return boardItemList.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        RecyclerView rankRecyclerView;
        ConstraintLayout rankBoard;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            rankBoard = itemView.findViewById(R.id.rank_board);
            titleTextView = itemView.findViewById(R.id.rank_title);
            rankRecyclerView = itemView.findViewById(R.id.rank_items_recyclerview);
        }
    }
}
