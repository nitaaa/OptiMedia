package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Game implements Parcelable {
    private int gameID;
    private int genreID;
    private String gameTitle, gameType;
    private boolean favourite, started, completed;

    public Game(int gameID, int genreID, String gameTitle, String gameType, boolean favourite, boolean started, boolean completed) {
        this.gameID = gameID;
        this.genreID = genreID;
        this.gameTitle = gameTitle;
        this.gameType = gameType;
        this.favourite = favourite;
        this.started = started;
        this.completed = completed;
    }

    public Game() {
    }

    protected Game(Parcel in) {
        gameID = in.readInt();
        genreID = in.readInt();
        gameTitle = in.readString();
        gameType = in.readString();
        favourite = in.readByte() != 0;
        started = in.readByte() != 0;
        completed = in.readByte() != 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public String toString() {
        return gameID + "," + genreID + "," + gameTitle + "," + gameType + "," + favourite + "," + started + "," + completed;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(gameID);
        parcel.writeInt(genreID);
        parcel.writeString(gameTitle);
        parcel.writeString(gameType);
        parcel.writeByte((byte) (favourite ? 1 : 0));
        parcel.writeByte((byte) (started ? 1 : 0));
        parcel.writeByte((byte) (completed ? 1 : 0));
    }
}
