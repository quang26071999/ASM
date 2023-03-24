package com.example.asm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.model.Slide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ImageViewHolder> {
    List<Slide> slideList;

    public SlideAdapter(List<Slide> slideList) {
        this.slideList = slideList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slide,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Slide slide = slideList.get(position);
            Picasso.get().load(slide.img).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (slideList != null){
            return slideList.size();
        }
        return 0;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_slide);
        }
    }
}
