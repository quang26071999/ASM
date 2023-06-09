package com.example.asm.controller.users;

import static com.example.asm.constant.url;

import android.util.Log;

import com.example.asm.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckLogin {
    public void checkLogin(String username,String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ControllerUser controllerUser = retrofit.create(ControllerUser.class);
        Call<List<User>> call = controllerUser.login(username,password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("TAG", "onResponse: thành công"+ response);
                List<User> list = response.body();
                Log.d("111111111", "onResponse: " + list.get(0).username);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("qqqqqqqqqq", "onFailure: ", t );
            }
        });
    }
}
