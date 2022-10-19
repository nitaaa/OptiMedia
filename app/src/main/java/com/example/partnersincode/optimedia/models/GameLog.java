package com.example.partnersincode.optimedia.models;

public class GameLog {
    private int GL_ID;
    private int gameID;
    private String glTitle;
    private String glNote;


    public GameLog(int Gl_ID, int gameID, String glTitle, String glNote) {
        this.GL_ID = Gl_ID;
        this.gameID = gameID;
        this.glTitle = glTitle;
        this.glNote = glNote;
    }

    public GameLog() {
    }

    @Override
    public String toString() {
        return GL_ID + "," + gameID + "," + glTitle + "," + glNote;
    }

    public int getML_ID() {
        return GL_ID;
    }

    public void setML_ID(int ML_ID) {
        this.GL_ID = ML_ID;
    }

    public int getMovieID() {
        return gameID;
    }

    public void setMovieID(int movieID) {
        this.gameID = movieID;
    }

    public String getGLTitle() {
        return glTitle;
    }

    public void setGLTitle(String gltitle) {
        this.glTitle = gltitle;
    }

    public String getGLNote() {
        return glNote;
    }

    public void setGLNote(String glNote) {
        this.glNote = glNote;
    }


}
