package com.example.partnersincode.optimedia.models;


import com.example.partnersincode.optimedia.Property;

public class Movie {
    //Properties
    private Property<Integer>  movieID = new Property<>();
    private Property<Integer> genreID = new Property<>();
    private Property<String> title =  new Property<>();
    private Property<Boolean> favourite =  new Property<>();
    private Property<Boolean> started = new Property<>();
    private Property<Boolean> complete = new Property<>();

    //Constructor
    public Movie(int movieID, int genreID, String title, boolean favourite, boolean started, boolean complete) {
        this.movieID.set(movieID);
        this.genreID.set(genreID);
        this.title.set(title);
        this.favourite.set(favourite);
        this.started.set(started);
        this.complete.set(complete);
    }

    //Accessors and Modifiers


    //Other methods
    /**
     Writes the details of a movie to the console.
     @return Void
     @throws Exception
     */
    //Other methods
    public void logGame()
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
