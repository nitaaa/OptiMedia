package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Book implements Parcelable {
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

    protected Book(Parcel in) {
        bookID = in.readInt();
        authorID = in.readInt();
        genreID = in.readInt();
        ISBN = in.readString();
        bookTitle = in.readString();
        favourite = in.readByte() != 0;
        started = in.readByte() != 0;
        completed = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(bookID);
        parcel.writeInt(authorID);
        parcel.writeInt(genreID);
        parcel.writeString(ISBN);
        parcel.writeString(bookTitle);
        parcel.writeByte((byte) (favourite ? 1 : 0));
        parcel.writeByte((byte) (started ? 1 : 0));
        parcel.writeByte((byte) (completed ? 1 : 0));
    }
}
