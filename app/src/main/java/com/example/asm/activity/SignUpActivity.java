package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.asm.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
    }
}