package com.example.partnersincode.optimedia.models;

import javafx.beans.property.*;

public class Series {
    //Properties
    private IntegerProperty seriesID = new SimpleIntegerProperty();
    private IntegerProperty genreID = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private BooleanProperty favourite = new SimpleBooleanProperty();
    private BooleanProperty started = new SimpleBooleanProperty();
    private BooleanProperty complete = new SimpleBooleanProperty();

    //Constructor
    public Series(int seriesID, int genreID, String title, boolean favourite, boolean started, boolean complete) {
        this.seriesID.set(seriesID);
        this.genreID.set(genreID);
        this.title.set(title);
        this.favourite.set(favourite);
        this.started.set(started);
        this.complete.set(complete);
    }

    //Accessors and Modifiers


    //Other methods
    /**
     Writes the details of a series to the console.
     @return Void
     @throws Exception
     */
    //Other methods
    public void logGame()
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

    public IntegerProperty seriesIDProperty() {
        return seriesID;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }
}
