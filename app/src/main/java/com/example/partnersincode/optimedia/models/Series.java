package com.example.partnersincode.optimedia.models;

import com.example.partnersincode.optimedia.Property;

//Adriaan Benn
//Changed Series and movie to inherit from another class to simplify implementation of recyclerview holders

public class Series extends WatchObject {
    //Properties
    private Property<Integer> seriesID;


    //Constructor
    public Series(int seriesID, int genreID, String title, boolean favourite, boolean started, boolean complete) {
        super(genreID, title, favourite,started,complete);
        this.seriesID = new Property<>(seriesID);

    }

    //Accessors and Modifiers


    //Other methods
    /**
     Writes the details of a series to the console.
     @return Void
     @throws Exception
     */
    //Other methods
    public void logSeries()
    {
        try{
            System.out.println("SeriesID: "+seriesID.get()+"\nGenreID: "+genreID.get()+"\nTitle: "+title.get()
                    +"\nFavourite: "+favourite.get()+"\nStarted: "+started.get()+"\nComplete: "+complete.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Added getters for attributes used in use case A02310
    public int getSeriesID() {
        return seriesID.get();
    }

    public Property seriesIDProperty() {
        return seriesID;
    }

    public String getTitle() {
        return title.get();
    }

    public Property titleProperty() {
        return title;
    }

    @Override
    public int getID() {
        return seriesID.get();
    }
}
