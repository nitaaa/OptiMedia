package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Library implements Parcelable {
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

    protected Library(Parcel in) {
        libraryID = in.readInt();
        libraryName = in.readString();
        libraryType = in.readString();
    }

    public static final Creator<Library> CREATOR = new Creator<Library>() {
        @Override
        public Library createFromParcel(Parcel in) {
            return new Library(in);
        }

        @Override
        public Library[] newArray(int size) {
            return new Library[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(libraryID);
        parcel.writeString(libraryName);
        parcel.writeString(libraryType);
    }
}
