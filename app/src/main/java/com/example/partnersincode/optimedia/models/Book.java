package com.example.partnersincode.optimedia.models;

public class Book {
    private int bookID;
    private int authorID;
    private int genreID;
    private String ISBN, bookTitle;
    private boolean favourite, started, completed;

    public Book(int bookID, int authorID, int genreID, String ISBN, String bookTitle, boolean favourite, boolean started, boolean completed) {
        this.bookID = bookID;
        this.authorID = authorID;
        this.genreID = genreID;
        this.ISBN = ISBN;
        this.bookTitle = bookTitle;
        this.favourite = favourite;
        this.started = started;
        this.completed = completed;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return bookID + "," + authorID + "," + genreID + "," + ISBN + "," + bookTitle + "," + favourite + "," + started + "," + completed;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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
