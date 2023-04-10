package com.example.asm.activity;

import static com.example.asm.constant.url;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm.R;
import com.example.asm.adapter.CommentAdapter;
import com.example.asm.controller.books.BookController;
import com.example.asm.model.Book;
import com.example.asm.model.Comment;
import com.example.asm.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailScreenActivity extends AppCompatActivity {
    ImageView detail_book_image,like;

    Button detail_btn;
    TextView detail_book_name,detail_book_description;
    RecyclerView rv_comment;
    CommentAdapter adapter;
    List<Comment> commentList = new ArrayList<>();
    boolean checkLike = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        getSupportActionBar().hide();
        detail_book_image = findViewById(R.id.detail_book_image);
        detail_book_name = findViewById(R.id.detail_book_name);
        detail_btn = findViewById(R.id.detail_btn);
        like = findViewById(R.id.like);
        detail_btn = findViewById(R.id.detail_btn);
        rv_comment = findViewById(R.id.rv_comment);
        detail_book_description = findViewById(R.id.detail_book_description);
        detail_book_description.setMovementMethod(new ScrollingMovementMethod());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_comment.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        getBook(id);
        commentList = getComment(id);
        adapter = new CommentAdapter(commentList);
        rv_comment.setAdapter(adapter);
        detail_btn.setOnClickListener(view -> {
            Intent intent1 = new Intent(this,BookContentActivity.class);
            intent1.putExtra("id",id);
            startActivity(intent1);
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLike) {
                    like.setImageResource(R.drawable.like);
                    checkLike = false;
                } else {
                    like.setImageResource(R.drawable.thumb_up_48px);
                    checkLike = true;
                }
            }
        });

    }
    public void getBook(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookController controller = retrofit.create(BookController.class);
        Call<Book> call = controller.getABook(id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.d("TAG", "onResponse: thành công"+ response);
                Book book = response.body();
                detail_book_name.setText(book.getBookName());
                detail_book_description.setText(book.getDescription());
                Picasso.get().load(url+book.getCoverImage()).into(detail_book_image);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail"+t);
            }
        });

    }
    private List<Comment> getComment(String id){

        List<Comment> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookController controller = retrofit.create(BookController.class);
        Call<List<Comment>> call = controller.getCommentOfBook(id);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                for (Comment comment: response.body()) {
                    list.add(comment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail"+t);
            }
        });
        return list;
    }
}