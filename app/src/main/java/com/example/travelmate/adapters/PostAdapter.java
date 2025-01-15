package com.example.travelmate.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelmate.R;
import com.example.travelmate.models.Post;
import com.example.travelmate.models.User;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;        // 帖子列表
    private List<User> users;        // 用户列表
    private OnItemClickListener listener;  // 点击事件监听器

    public PostAdapter(List<Post> posts, List<User> users, OnItemClickListener listener) {
        this.posts = posts;
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each post
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        // 获取当前帖子的对象
        Post post = posts.get(position);

        // 设置帖子标题和内容摘要
        holder.titleTextView.setText(post.getTitle());
        holder.summaryTextView.setText(post.getSummary());

        // 设置发帖人的用户名
        User postUser = getUserByUsername(post.getUsername());
        if (postUser != null) {
            holder.usernameTextView.setText(postUser.getUsername());
        }

        // 处理点击事件
        holder.itemView.setOnClickListener(v -> listener.onItemClick(post)); // 向Fragment传递Post对象
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // 用于通过用户名获取用户对象
    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;  // 如果没有找到对应的用户
    }

    // ViewHolder类，用于缓存每个项的视图组件
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView summaryTextView;
        TextView usernameTextView;

        public PostViewHolder(View itemView) {
            super(itemView);
            // 获取每一项视图的引用
            titleTextView = itemView.findViewById(R.id.post_title);
            summaryTextView = itemView.findViewById(R.id.post_summary);
            usernameTextView = itemView.findViewById(R.id.post_username);
        }
    }

    // 点击事件接口
    public interface OnItemClickListener {
        void onItemClick(Post post);
    }
}
