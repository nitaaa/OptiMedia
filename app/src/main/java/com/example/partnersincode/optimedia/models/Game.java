package com.example.partnersincode.optimedia.models;

import com.example.partnersincode.optimedia.Property;



public class Game {
    //Properties
    private Property<Integer> gameID = new Property<>();
    private Property<Integer> genreID = new Property<>();
    private Property<String> title = new Property<>();
    private Property<String> type = new Property<>();
    private Property<Boolean> favourite = new Property<>();
    private Property<Boolean> started = new Property<>();
    private Property<Boolean> complete = new Property<>();

    //Constructor
    public Game(int gameID, int genreID, String title, String type, boolean favourite, boolean started, boolean complete) {
        this.gameID.set(gameID);
        this.genreID.set(genreID);
        this.title.set(title);
        this.type.set(type);
        this.favourite.set(favourite);
        this.started.set(started);
        this.complete.set(complete);
    }

    //Accessors and Modifiers


    /**
     Writes the details of a game to the console.
     @return Void
     @throws Exception
     */
    //Other methods
    public void logGame()
    {
        try{
            System.out.println("GameID: "+gameID.get()+"\nGenreID: "+genreID.get()+"\nTitle: "+title.get()+"\nType: "+type.get()
                    +"\nFavourite: "+favourite.get()+"\nStarted: "+started.get()+"\nComplete: "+complete.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
