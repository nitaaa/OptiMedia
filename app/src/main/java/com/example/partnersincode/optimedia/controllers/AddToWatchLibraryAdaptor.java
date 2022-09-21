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
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.ArrayList;

public class AddToWatchLibraryAdaptor extends RecyclerView.Adapter<AddToWatchLibraryAdaptor.WatchObjectViewHolder> {

    private View.OnClickListener onClickListener;

    ArrayList<WatchObject> watchObjects;
    static ArrayList<WatchObject> selectedObjects;

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



        public WatchObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            mediaTitle =  itemView.findViewById(R.id.recyc_mediaTitle);
            mediaType = itemView.findViewById(R.id.recyc_mediaType);

        }

        public void setHeldObject(WatchObject heldObject) {
            this.heldObject = heldObject;
            mediaTitle.setText(heldObject.getTitle());
            selected = false;
            if(heldObject instanceof Movie) mediaType.setText(R.string.movie);
            else mediaType.setText(R.string.series);
        }

        public WatchObject getHeldObject()
        {
            return heldObject;
        }

        public void setSelected()
        {
            //If it isn't selected by clicked on, make it green(or other colour) and set selected
            if (!selected) {
                selected = true;
                selectedObjects.add(getHeldObject());
                itemView.setBackgroundResource(R.color.selectedColour);
            }
            else
            {
                //If already selected and clicked on, set unselected
                selected = false;
                itemView.setBackgroundResource(R.color.design_default_color_background);
                selectedObjects.remove(getHeldObject());
            }
        }

        public Boolean isSelected() {
            return selected;
        }
    }

    public AddToWatchLibraryAdaptor(DatabaseHandler db)
    {
        this.db = db;
        getWatchObjects();
        selectedObjects = new ArrayList<>();

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

        watchObjects = db.getMoviesAndSeries("");
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ArrayList<WatchObject> getSelectedWatchObjects()
    {
        return selectedObjects;
    }


    public void changeDataSet(String search)
    {
        watchObjects= db.getMoviesAndSeries(search);

        notifyDataSetChanged();
    }


}
