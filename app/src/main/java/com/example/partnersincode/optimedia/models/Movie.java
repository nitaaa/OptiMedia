package com.example.partnersincode.optimedia.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.partnersincode.optimedia.Property;

//Adriaan Benn
//Changed Series and movie to inherit from another class to simplify implementation of recyclerview holders
public class Movie extends WatchObject implements Parcelable {
    //Properties
    private int  movieID;


    //Constructor
    public Movie(int movieID, int genreID, String title, String link, boolean favourite, boolean started, boolean complete) {
        super(genreID,title,link,favourite,started,complete);
        //super.setMovie();
        this.movieID = movieID;
    }

    public Movie() {

    }

    protected Movie(Parcel in) {
        movieID = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //Added getters for attributes used in use case A02310
    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getID() {
        return movieID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        super.writeToParcel(parcel,i);
        parcel.writeInt(movieID);
    }
}
