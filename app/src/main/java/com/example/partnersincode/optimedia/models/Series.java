package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.partnersincode.optimedia.Property;

//Adriaan Benn
//Changed Series and movie to inherit from another class to simplify implementation of recyclerview holders

public class Series extends WatchObject implements Parcelable {
    //Properties
    private int seriesID;


    //Constructor
    public Series(int seriesID, int genreID, String title, String link, boolean favourite, boolean started, boolean complete) {
        super(genreID, title, link, favourite,started,complete);
//        super.setSeries();
        this.seriesID = seriesID;

    }

    public Series() {
        super();
    }

    protected Series(Parcel in) {
        seriesID = in.readInt();
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

    //Accessors and Modifiers
    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    //Added getters for attributes used in use case A02310
    public int getSeriesID() {
        return seriesID;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getID() {
        return seriesID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        super.writeToParcel(parcel,i);
        parcel.writeInt(seriesID);
    }
}
