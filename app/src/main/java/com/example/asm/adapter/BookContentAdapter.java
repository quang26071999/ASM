package com.example.asm.adapter;



import static com.example.asm.constant.url;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.activity.BookContentActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookContentAdapter extends RecyclerView.Adapter<BookContentAdapter.viewHolder> {
    List<String> list;

    public BookContentAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_content,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String link = list.get(position);
        Picasso.get().load(url+link).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.book_content_image);
        }
    }
}
