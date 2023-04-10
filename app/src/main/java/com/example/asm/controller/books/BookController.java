package com.example.asm.controller.books;

import com.example.asm.model.Book;
import com.example.asm.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookController {
    @GET("/book")
    Call<List<Book>> getAllBook();
    @GET("/book/{id}")
    Call<Book> getABook(@Path("id") String id);
    @GET("/comment/bookComment/{id}")
    Call<List<Comment>> getCommentOfBook(@Path("id") String id);
    @GET("/book/imageOfBook/{id}")
    Call<List<String>> getImageOfBook(@Path("id") String id);
}
