package com.example.partnersincode.optimedia.models;

import java.io.Serializable;

public class MovieLog implements Serializable {
    private int ML_ID;
    private int movieID;
    private String m_note;
    private String m_timestamp;

    public MovieLog(int ML_ID, int movieID, String m_timestamp, String m_note) {
        this.ML_ID = ML_ID;
        this.movieID = movieID;
        this.m_note = m_note;
        this.m_timestamp = m_timestamp;
    }

    public MovieLog() {
    }

    @Override
    public String toString() {
        return ML_ID + "," + movieID + "," + m_note + "," + m_timestamp;
    }

    public int getML_ID() {
        return ML_ID;
    }

    public void setML_ID(int ML_ID) {
        this.ML_ID = ML_ID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getM_note() {
        return m_note;
    }

    public void setM_note(String m_note) {
        this.m_note = m_note;
    }

    public String getM_timestamp() {
        return m_timestamp;
    }

    public void setM_timestamp(String m_timestamp) {
        this.m_timestamp = m_timestamp;
    }
}
