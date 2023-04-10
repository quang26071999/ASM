package com.example.asm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.model.Comment;

import org.w3c.dom.Text;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.viewHolder> {
    List<Comment> list;

    public CommentAdapter(List<Comment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Comment comment = list.get(position);
        holder.commentItem_fullName.setText(comment.getUserId().fullName);
        holder.commentItem_content.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView commentItem_fullName,commentItem_content;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            commentItem_content = itemView.findViewById(R.id.comment_item_content);
            commentItem_fullName = itemView.findViewById(R.id.comment_item_fullName);
        }
    }
}
