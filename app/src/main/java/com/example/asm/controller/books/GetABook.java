package com.example.asm.controller.books;

import static com.example.asm.constant.url;

import android.util.Log;

import com.example.asm.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetABook {
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
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail"+t);
            }
        });
    }
}
