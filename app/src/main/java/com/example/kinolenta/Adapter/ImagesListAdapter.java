package com.example.kinolenta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kinolenta.R;

import java.util.List;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ViewHolder> {
    List<String> images;
    Context context;

    public ImagesListAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ImagesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholded_ditail_images, parent, false);
        context = parent.getContext();

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesListAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            pic = itemView.findViewById(R.id.itemImages);
        }
    }
}
