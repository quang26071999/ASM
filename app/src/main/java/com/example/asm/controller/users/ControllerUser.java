package com.example.asm.controller.users;

import com.example.asm.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ControllerUser {
    @POST("/user/checkLogin")
    @FormUrlEncoded
    Call<List<User>> login(@Field("username") String username, @Field("password") String password);
    @POST("/user")
    Call<User> signUp(@Body User user);
}
