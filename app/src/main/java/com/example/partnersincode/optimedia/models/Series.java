package com.example.partnersincode.optimedia.models;

import com.example.partnersincode.optimedia.Property;

//Adriaan Benn
//Changed Series and movie to inherit from another class to simplify implementation of recyclerview holders

public class Series extends WatchObject {
    //Properties
    private int seriesID;


    //Constructor
    public Series(int seriesID, int genreID, String title, boolean favourite, boolean started, boolean complete) {
        super(genreID, title, favourite,started,complete);
        this.seriesID = seriesID;

    }

    //Accessors and Modifiers



    //Added getters for attributes used in use case A02310
    public int getSeriesID() {
        return seriesID;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getID() {
        return seriesID;
    }
}
