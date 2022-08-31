package com.example.partnersincode.optimedia.controllers;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.ArrayList;

public class AddToWatchLibraryAdaptor extends RecyclerView.Adapter<AddToWatchLibraryAdaptor.WatchObjectViewHolder> {

    private View.OnClickListener onClickListener;

    ArrayList<WatchObject> watchObjects;

    // make use of database handler to get media objects
    DatabaseHandler db;

    public static class WatchObjectViewHolder extends RecyclerView.ViewHolder
    {

        //UI element
        TextView mediaTitle;
        TextView mediaType;

        //Object related fields
        WatchObject heldObject;
        Boolean selected;
        int defaultColour;


        public WatchObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            mediaTitle =  itemView.findViewById(R.id.recyc_mediaTitle);
            defaultColour = itemView.getDrawingCacheBackgroundColor();
        }

        public void setHeldObject(WatchObject heldObject) {
            this.heldObject = heldObject;
            mediaTitle.setText(heldObject.gettitle());
            selected = false;
            if(heldObject instanceof Movie) mediaType.setText(R.string.movie);
            else mediaType.setText(R.string.series);
        }

        public void setSelected()
        {
            if (!selected) {
                selected = true;
                itemView.setBackgroundResource(R.color.selectedColour);
            }
            else
            {
                selected = false;
                itemView.setBackgroundColor(defaultColour);
            }
        }
    }

    public AddToWatchLibraryAdaptor(DatabaseHandler db)
    {
        this.db = db;
        getWatchObjects();

    }


    @NonNull
    @Override
    public WatchObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflate the cardview and set up view holder
        View view = LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.recycler_media_object,
                        parent, false);
        return new WatchObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchObjectViewHolder holder, int position) {

        //set up a viewHolder for object
        holder.setHeldObject(watchObjects.get(position));
        holder.itemView.setOnClickListener(onClickListener);
    }




    @Override
    public int getItemCount() {
        return watchObjects.size();
    }


    private void getWatchObjects()
    {

        watchObjects = db.getMoviesAndSeries();
        notifyDataSetChanged();
    }


}
