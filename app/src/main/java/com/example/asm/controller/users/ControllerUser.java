package com.example.asm.controller.users;

import com.example.asm.model.User;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ControllerUser {
    @POST("/user/checkLogin")
    Call<User> login(@Body User user);
}
