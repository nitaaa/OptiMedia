package com.example.partnersincode.optimedia.models;

import com.example.partnersincode.optimedia.DatabaseHandler;

public class Library {
    private int libraryID;
    private String libraryName;
    private String libraryType;

    public Library(int libraryID, String libraryName, String libraryType) {
        this.libraryID = libraryID;
        this.libraryName = libraryName;
        this.libraryType = libraryType;
    }

    public Library() {
    }

    public int getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryType() {
        return libraryType;
    }

    public void setLibraryType(String libraryType) {
        this.libraryType = libraryType;
    }

    @Override
    public String toString() {
        return libraryID + "," + libraryName + "," + libraryType;
    }
}
