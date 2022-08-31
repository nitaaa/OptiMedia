package com.example.partnersincode.optimedia.models;


import com.example.partnersincode.optimedia.Property;

//Adriaan Benn
//Changed Series and movie to inherit from another class to simplify implementation of recyclerview holders
public class Movie extends WatchObject {
    //Properties
    private Property<Integer>  movieID;


    //Constructor
    public Movie(int movieID, int genreID, String title, boolean favourite, boolean started, boolean complete) {
        super(genreID,title,favourite,started,complete);
        this.movieID = new Property<>(movieID);
    }

    //Accessors and Modifiers


    //Other methods
    /**
     Writes the details of a movie to the console.
     @return Void
     @throws Exception
     */
    //Other methods
    public void logMovie()
    {
        try{
            System.out.println("MovieID: "+movieID.get()+"\nGenreID: "+genreID.get()+"\nTitle: "+title.get()
                    +"\nFavourite: "+favourite.get()+"\nStarted: "+started.get()+"\nComplete: "+complete.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Added getters for attributes used in use case A02310
    public int getMovieID() {
        return movieID.get();
    }

    public Property movieIDProperty() {
        return movieID;
    }

    public String getTitle() {
        return title.get();
    }

    public Property titleProperty() {
        return title;
    }
}
