package com.example.partnersincode.optimedia.models;

import com.example.partnersincode.optimedia.Property;

public class WatchObject
{
    protected int WLI_ID;
    protected int genreID;
    protected String title;
    protected Boolean favourite,started, complete;

    public WatchObject(int genreID, String title, Boolean favourite, Boolean started, Boolean complete) {
        this.genreID = genreID;
        this.title = title;
        this.favourite = favourite;
        this.started = started;
        this.complete = complete;
    }

    public WatchObject() {

    }

    public int getWLI_ID() {
        return WLI_ID;
    }

    public void setWLI_ID(int WLI_ID) {
        this.WLI_ID = WLI_ID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public int getID()
    {
        return -1;
    }

//    public WatchObject(int genreID, String title, boolean favourite, boolean started, boolean complete) {
//        this.genreID = new Property<>(genreID);
//        this.title = new Property<>(title);
//        this.favourite = new Property<>(favourite);
//        this.started = new Property<>(started);
//        this.complete = new Property<>(complete);
//    }
//
//    public int getGenreID()
//    {
//        return genreID.get();
//    }
//
//    public String gettitle()
//    {
//        return title.get();
//    }
//
//    public Boolean isFavourited()
//    {
//        return favourite.get();
//    }
//
//    public boolean isStarted()
//    {
//        return started.get();
//    }
//    public boolean isComplete()
//    {
//        return complete.get();
//    }
//
//    public String toString()
//    {
//        return title.get();
//    }
//
//    public int getID()
//    {
//        return -1;
//    }
}
