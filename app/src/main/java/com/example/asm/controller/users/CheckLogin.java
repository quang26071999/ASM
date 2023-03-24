package com.example.asm.controller.users;

import android.util.Log;

import com.example.asm.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckLogin {
    public void checkLogin(String link,User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ControllerUser controllerUser = retrofit.create(ControllerUser.class);
        Call<User> call = controllerUser.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: thành công"+ response);
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("qqqqqqqqqq", "onFailure: ", t );
            }
        });
    }
}
