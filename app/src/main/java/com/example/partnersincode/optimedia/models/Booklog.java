package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Booklog implements Parcelable {
    private int BL_ID;
    private int bookID;
    private String blTitle;
    private String blNote;
    private int blPageNumber;

    public Booklog(int BL_ID, int bookID, String lbTitle, String lbNote, int blPageNumber) {
        this.BL_ID = BL_ID;
        this.bookID = bookID;
        this.blTitle = lbTitle;
        this.blNote = lbNote;
        this.blPageNumber = blPageNumber;
    }

    protected Booklog(Parcel in) {
        BL_ID = in.readInt();
        bookID = in.readInt();
        blTitle = in.readString();
        blNote = in.readString();
        blPageNumber = in.readInt();
    }

    public static final Creator<Booklog> CREATOR = new Creator<Booklog>() {
        @Override
        public Booklog createFromParcel(Parcel in) {
            return new Booklog(in);
        }

        @Override
        public Booklog[] newArray(int size) {
            return new Booklog[size];
        }
    };

    public Booklog() {

    }

    public int getBL_ID() {
        return BL_ID;
    }

    public void setBL_ID(int BL_ID) {
        this.BL_ID = BL_ID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBlTitle() {
        return blTitle;
    }

    public void setBlTitle(String blTitle) {
        this.blTitle = blTitle;
    }

    public String getBlNote() {
        return blNote;
    }

    public void setBlNote(String blNote) {
        this.blNote = blNote;
    }

    public int getBlPageNumber() {
        return blPageNumber;
    }

    public void setBlPageNumber(int blPageNumber) {
        this.blPageNumber = blPageNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(BL_ID);
        parcel.writeInt(bookID);
        parcel.writeString(blTitle);
        parcel.writeString(blNote);
        parcel.writeInt(blPageNumber);
    }
}
