package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.asm.R;
import com.squareup.picasso.Picasso;

public class DetailScreenActivity extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        img = findViewById(R.id.detail_image_book);
        Picasso.get().load("https://riki.edu.vn/wp-content/uploads/2019/12/truyen-manga-1-1024x592.jpg").into(img);
    }
}