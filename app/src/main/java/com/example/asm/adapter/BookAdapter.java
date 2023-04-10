package com.example.asm.adapter;

import static com.example.asm.constant.url;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        Picasso.get().load(url+book.coverImage).into(holder.item_book_img);
        holder.item_book_name.setText(book.bookName);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        TextView item_book_name;
        ImageView item_book_img;
        CardView book_card;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            item_book_name = itemView.findViewById(R.id.item_book_name);
            item_book_img = itemView.findViewById(R.id.item_book_img);
            book_card = itemView.findViewById(R.id.book_card);

        }
    }
}
