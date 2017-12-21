package com.example.dsgandroidapp.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dsgandroidapp.R;
import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenuesPhotos;

/**
 * Created by Srinivas on 12/20/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private VenuesResponseVenuesPhotos[] data;
    private Context context;


    public PhotosAdapter(Context context, VenuesResponseVenuesPhotos[] data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data != null) {
            if (data.length != 0) {
                String imageUrl = data[position].getUrl();
                //glide library
                Glide.with(context)
                        .load(imageUrl)
                        .into(holder.ivPhoto);
            }
        }

    }

    @Override
    public int getItemCount() {

        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.photo_venue);

        }


//            public void click(final Venue venueData, final PhotoAdapter.OnItemClickListener listener) {
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        listener.onClick(venueData);
//                    }
//                });
//            }


    }

}
