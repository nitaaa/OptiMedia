package com.example.partnersincode.optimedia.models;


import com.example.partnersincode.optimedia.Property;

public class WatchLibrary
{
    Property<Integer> WLI_ID = new Property<>();
    Property<Integer> libraryID = new Property<>();
    Property<String> wl_Name = new Property<>();

    //This will be changed to match MVC principles
    public WatchLibrary(String wl_Name) {
        this.wl_Name.set(wl_Name);

        //get id's from the database
    }

    public String toString()
    {
        return wl_Name.get();
    }


}
