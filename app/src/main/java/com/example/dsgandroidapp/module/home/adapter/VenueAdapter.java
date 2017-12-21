package com.example.dsgandroidapp.module.home.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.dsgandroidapp.R;
import com.example.dsgandroidapp.mvp.Model.Venue;
import com.example.dsgandroidapp.mvp.Model.Venues;
import com.example.dsgandroidapp.mvp.Model.VenuesResponseVenues;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Srinivas on 12/20/2017.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Venue> mVenuesList = new ArrayList<>();
    private Context context;

    public VenueAdapter(LayoutInflater inflater, Context context) {
        mLayoutInflater = inflater;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.venue_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mVenuesList.get(position));


        if (position % 4 == 0) {
            holder.mVenueLocation.setBackgroundColor(ContextCompat.getColor(context, R.color.brown));
        } else if (position % 4 == 1) {
            holder.mVenueLocation.setBackgroundColor(ContextCompat.getColor(context, R.color.lightwhite));
        } else if (position % 4 == 2) {
            holder.mVenueLocation.setBackgroundColor(ContextCompat.getColor(context, R.color.different));
        } else if (position % 4 == 3) {
            holder.mVenueLocation.setBackgroundColor(ContextCompat.getColor(context, R.color.light));
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("adapter size3" + mVenuesList.size());
        return mVenuesList.size();
    }

    public void addVenues(List<Venue> venues) {
        System.out.println("adapter size" + venues.size());
        for (Venue venue : venues) {
            if (venue.getVenue().getLocation() != null) {
                mVenuesList.add(venue);
            }
        }

        System.out.println("adapter size2" + mVenuesList.size());
        notifyDataSetChanged();
    }

    public void clearVenues() {
        mVenuesList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.info_text)
        protected TextView mVenueLocation;
        @Bind(R.id.distance_text)
        protected TextView distance;
        private Context mContext;
        private Venue mVenues;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Venue venues) {
            mVenues = venues;
            System.out.println("name " + mVenues.getVenue().getName());
            mVenueLocation.setText(mVenues.getVenue().getLocation().getAddress() + ", " + mVenues.getVenue().getLocation().getCity()
                    + ", " + mVenues.getVenue().getLocation().getState() + ", " + mVenues.getVenue().getLocation().getCountry() + ", " + mVenues.getVenue().getLocation().getPostalCode());
            distance.setText(String.format("%.1f", mVenues.getDistance()) + " mi");
        }

        @Override
        public void onClick(View v) {
            if (mVenueClickListener != null) {
                mVenueClickListener.onClick(mVenues, getAdapterPosition());
            }
        }
    }

    public void setVenueClickListener(OnVenueClickListener listener) {
        mVenueClickListener = listener;
    }

    private OnVenueClickListener mVenueClickListener;

    public interface OnVenueClickListener {

        void onClick(Venue venues, int position);
    }
}

