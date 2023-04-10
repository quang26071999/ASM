package com.example.asm.activity;

import static com.example.asm.constant.url;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.asm.R;
import com.example.asm.adapter.BookContentAdapter;
import com.example.asm.controller.books.BookController;
import com.example.asm.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookContentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BookContentAdapter adapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);
        recyclerView = findViewById(R.id.bookContent);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        getSupportActionBar().hide();
        list=getImage(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookContentAdapter(list);
        recyclerView.setAdapter(adapter);

    }
    public List<String> getImage(String id){
        List<String> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookController controller = retrofit.create(BookController.class);
        Call<List<String>> call = controller.getImageOfBook(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                for (String str: response.body()) {
                    list.add(str);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("yyyyyyyyyyy", "onResponse: Fail"+t);
            }
        });
        return list;
    }

}