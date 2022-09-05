package com.example.partnersincode.optimedia.models;


import com.example.partnersincode.optimedia.Property;

//Adriaan Benn
//Changed Series and movie to inherit from another class to simplify implementation of recyclerview holders
public class Movie extends WatchObject {
    //Properties
    private int  movieID;


    //Constructor
    public Movie(int movieID, int genreID, String title, boolean favourite, boolean started, boolean complete) {
        super(genreID,title,favourite,started,complete);
        this.movieID = movieID;
    }



    //Added getters for attributes used in use case A02310
    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getID() {
        return movieID;
    }
}
