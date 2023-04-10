package com.example.asm.controller.users;

import static com.example.asm.constant.url;

import android.util.Log;

import com.example.asm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp {
    public void signUp( User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ControllerUser controllerUser = retrofit.create(ControllerUser.class);
        Call<User> call = controllerUser.signUp(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: thành công"+ response);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail");
            }
        });
    }
}
