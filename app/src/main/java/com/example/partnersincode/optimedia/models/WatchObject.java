package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.partnersincode.optimedia.Property;

public class WatchObject implements Parcelable
{
    protected int WLI_ID;
    protected int genreID;
    protected String title;
    protected String link;
    protected Boolean favourite,started, complete;
//    protected String type;

    public WatchObject(int genreID, String title, String link, Boolean favourite, Boolean started, Boolean complete) {
        this.genreID = genreID;
        this.title = title;
        this.link = link;
        this.favourite = favourite;
        this.started = started;
        this.complete = complete;
    }

    public WatchObject() {

    }


    protected WatchObject(Parcel in) {
        WLI_ID = in.readInt();
        genreID = in.readInt();
        title = in.readString();
        link = in.readString();
        byte tmpFavourite = in.readByte();
        favourite = tmpFavourite == 0 ? null : tmpFavourite == 1;
        byte tmpStarted = in.readByte();
        started = tmpStarted == 0 ? null : tmpStarted == 1;
        byte tmpComplete = in.readByte();
        complete = tmpComplete == 0 ? null : tmpComplete == 1;
    }

    public static final Creator<WatchObject> CREATOR = new Creator<WatchObject>() {
        @Override
        public WatchObject createFromParcel(Parcel in) {
            return new WatchObject(in);
        }

        @Override
        public WatchObject[] newArray(int size) {
            return new WatchObject[size];
        }
    };


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(WLI_ID);
        parcel.writeInt(genreID);
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeByte((byte) (favourite == null ? 0 : favourite ? 1 : 2));
        parcel.writeByte((byte) (started == null ? 0 : started ? 1 : 2));
        parcel.writeByte((byte) (complete == null ? 0 : complete ? 1 : 2));
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
