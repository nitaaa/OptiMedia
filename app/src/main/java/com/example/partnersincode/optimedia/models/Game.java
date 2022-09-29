package com.example.partnersincode.optimedia.models;

public class Game {
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
}
