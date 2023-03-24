package com.example.asm.activity;



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
import com.example.asm.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    ImageView img1, img2, img3;
    RecyclerView rv1,rv2;
    BookAdapter adapter;
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
        viewFlipper.setFlipInterval(4000);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        rv1.setLayoutManager(linearLayoutManager);
        rv2.setLayoutManager(linearLayoutManager2);
        bookList = addBook();
        adapter = new BookAdapter(bookList);
        Log.d("TAG", "onCreate: "+bookList.size());
        rv1.setAdapter(adapter);
        rv2.setAdapter(adapter);
        getSupportActionBar().hide();
    }
    List<Book> addBook(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Truyện 1","","","","https://i.pinimg.com/564x/12/c9/54/12c954edf841e5da800865eff66aa90f.jpg",null));
        books.add(new Book("Truyện 2","","","","https://i.pinimg.com/474x/0e/c7/fe/0ec7fea6fa49e3f7ef4cc24cfe603096.jpg",null));
        books.add(new Book("Truyện 3","","","","https://i.pinimg.com/236x/df/29/bc/df29bc3c5cbe9d1298ce4377cc187eba.jpg",null));
        books.add(new Book("Truyện 4","","","","https://i.pinimg.com/736x/19/7b/a1/197ba147ba0a9b9d3f3292e04bec7fe0.jpg",null));
        books.add(new Book("Truyện 5","","","","https://i.pinimg.com/originals/7c/04/3d/7c043d32991624162de382b7640f76e1.jpg",null));
        return books;
    }
}