package com.example.partnersincode.optimedia.models;

public class Author {
    private int authorID;
    private String authorName, authorSurname;

    public Author(int authorID, String authorName, String authorSurname) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return authorID + "," + authorName + "," + authorSurname;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

}
