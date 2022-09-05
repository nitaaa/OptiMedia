package com.example.partnersincode.optimedia.models;

import com.example.partnersincode.optimedia.Property;

public class WatchObject
{
    protected Property<Integer> genreID;
    protected Property<String> title;
    protected Property<Boolean> favourite;
    protected Property<Boolean> started;
    protected Property<Boolean> complete;

    public WatchObject(int genreID, String title, boolean favourite, boolean started, boolean complete) {
        this.genreID = new Property<>(genreID);
        this.title = new Property<>(title);
        this.favourite = new Property<>(favourite);
        this.started = new Property<>(started);
        this.complete = new Property<>(complete);
    }

    public int getGenreID()
    {
        return genreID.get();
    }

    public String gettitle()
    {
        return title.get();
    }

    public Boolean isFavourited()
    {
        return favourite.get();
    }

    public boolean isStarted()
    {
        return started.get();
    }
    public boolean isComplete()
    {
        return complete.get();
    }

    public String toString()
    {
        return title.get();
    }

    public int getID()
    {
        return -1;
    }
}
