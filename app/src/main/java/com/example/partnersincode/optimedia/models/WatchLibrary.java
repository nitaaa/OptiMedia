package com.example.partnersincode.optimedia.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WatchLibrary
{
    IntegerProperty WLI_ID = new SimpleIntegerProperty();
    IntegerProperty libraryID = new SimpleIntegerProperty();
    StringProperty wl_Name = new SimpleStringProperty();

    //This will be changed to match MVC principles
    public WatchLibrary(String wl_Name) {
        this.wl_Name.setValue(wl_Name);

        //get id's from the database
    }

    public String toString()
    {
        return wl_Name.get();
    }


}
