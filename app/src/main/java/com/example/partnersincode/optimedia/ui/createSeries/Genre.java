package com.example.partnersincode.optimedia.ui.createSeries;

public class Genre {
    private int genreID;
    private String genreName;

    public Genre(int genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public Genre() {
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }
}
