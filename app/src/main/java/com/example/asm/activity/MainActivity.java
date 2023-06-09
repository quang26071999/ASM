package com.example.asm.activity;



import static com.example.asm.constant.url;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.asm.R;
import com.example.asm.adapter.BookAdapter;
import com.example.asm.adapter.RecyclerviewItemOnclick;
import com.example.asm.controller.books.BookController;
import com.example.asm.controller.books.GetAllBook;
import com.example.asm.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kotlin.experimental.BitwiseOperationsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    ImageView img1, img2, img3;
    RecyclerView rv1,rv2;
    public BookAdapter adapter;
    List<Book> bookList;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        rv1.addOnItemTouchListener(
                new RecyclerviewItemOnclick(this, rv1 ,new RecyclerviewItemOnclick.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this,DetailScreenActivity.class);
                        String id = bookList.get(position).getId();
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
    void initial(){
        viewFlipper = findViewById(R.id.view_flip);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        rv1 = findViewById(R.id.rv_1);
        rv2 = findViewById(R.id.rv_2);
        Picasso.get().load("https://riki.edu.vn/wp-content/uploads/2019/12/truyen-manga-1-1024x592.jpg").into(img1);
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpE__grCNqCDreQ-TrtEQ_dIhmSMuvxG6q1Q&usqp=CAU").into(img2);
        Picasso.get().load("https://mcdn.coolmate.me/uploads/April2022/nhung-thuat-ngu-trong-anime-manga-7.jpg").into(img3);
        viewFlipper.setFlipInterval(3000);
        getBooks();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        rv1.setLayoutManager(linearLayoutManager);
        rv2.setLayoutManager(linearLayoutManager2);
        bookList = getBooks();
        adapter = new BookAdapter(bookList);
        rv1.setAdapter(adapter);
        rv2.setAdapter(adapter);
        getSupportActionBar().hide();

    }
    public List<Book> getBooks(){
        List<Book> bookList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookController controller = retrofit.create(BookController.class);
        Call<List<Book>> call = controller.getAllBook();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                for (Book book : response.body()) {
                    bookList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail"+t);
            }
        });

        return bookList;
    }
}