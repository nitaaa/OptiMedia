package com.example.partnersincode.optimedia.models;


import com.example.partnersincode.optimedia.Property;

public class Library
{
    Property<Integer> libraryID;
    Property<String> libraryName;
    Property<String> libraryType;

    //This will be changed to match MVC principles

    /**
     * This constructor made with creating library objects with all the data fields in mind
     *
     * @param libraryID Database ID of library
     * @param libraryName Database libraryName, as defined by users
     * @param libraryType Database library type
     */
    public Library(int libraryID, String libraryName, String libraryType) {
        this.libraryID = new Property<>(libraryID);
        this.libraryName = new Property<>(libraryName);
        this.libraryType = new Property<>(libraryType);

    }

    @Override
    public String toString()
    {
        return libraryName.get();
    }


}
