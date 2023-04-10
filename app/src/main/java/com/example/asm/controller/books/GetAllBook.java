package com.example.asm.controller.books;

import static com.example.asm.constant.url;

import android.util.Log;

import com.example.asm.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllBook {
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
                Log.d("TAG", "onResponse: thành công"+ response);
                for (Book book : response.body()) {
                    bookList.add(book);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail"+t);
            }
        });

        return bookList;
    }
}
