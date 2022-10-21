package com.example.partnersincode.optimedia.models;

import java.io.Serializable;

public class GameLog implements Serializable {
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

    public int getGL_ID() {
        return GL_ID;
    }

    public void setGL_ID(int GL_ID) {
        this.GL_ID = GL_ID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGLTitle() {
        return glTitle;
    }

    public void setGLTitle(String glTitle) {
        this.glTitle = glTitle;
    }

    public String getGLNote() {
        return glNote;
    }

    public void setGLNote(String glNote) {
        this.glNote = glNote;
    }


}
