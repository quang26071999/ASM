package com.example.asm.fragment;

import static com.example.asm.constant.url;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.asm.R;
import com.example.asm.activity.DetailScreenActivity;
import com.example.asm.activity.MainActivity;
import com.example.asm.adapter.BookAdapter;
import com.example.asm.adapter.RecyclerviewItemOnclick;
import com.example.asm.controller.books.BookController;
import com.example.asm.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    ViewFlipper viewFlipper;
    ImageView img1, img2, img3;
    RecyclerView rv1,rv2;
    public BookAdapter adapter;
    List<Book> bookList;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initial(view);
        rv1.addOnItemTouchListener(
                new RecyclerviewItemOnclick(view.getContext(), rv1 ,new RecyclerviewItemOnclick.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(view.getContext(), DetailScreenActivity.class);
                        String id = bookList.get(position).getId();
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }
    void initial(View view){
        viewFlipper = view.findViewById(R.id.view_flip);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        rv1 = view.findViewById(R.id.rv_1);
        rv2 = view.findViewById(R.id.rv_2);
        Picasso.get().load("https://riki.edu.vn/wp-content/uploads/2019/12/truyen-manga-1-1024x592.jpg").into(img1);
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpE__grCNqCDreQ-TrtEQ_dIhmSMuvxG6q1Q&usqp=CAU").into(img2);
        Picasso.get().load("https://mcdn.coolmate.me/uploads/April2022/nhung-thuat-ngu-trong-anime-manga-7.jpg").into(img3);
        viewFlipper.setFlipInterval(3000);
        getBooks();
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager2 = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        rv1.setLayoutManager(linearLayoutManager);
        rv2.setLayoutManager(linearLayoutManager2);
        bookList = getBooks();
        adapter = new BookAdapter(bookList);
        rv1.setAdapter(adapter);
        rv2.setAdapter(adapter);

    }
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
                for (Book book : response.body()) {
                    bookList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("TAG", "onResponse: Fail"+t);
            }
        });

        return bookList;
    }
}