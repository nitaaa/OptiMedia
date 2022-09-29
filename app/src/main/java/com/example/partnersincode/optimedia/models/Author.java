package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Author implements Parcelable {
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

    public String getFullName() {
        return authorName + " " + authorSurname;
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

    protected Author(Parcel in) {
        authorID = in.readInt();
        authorName = in.readString();
        authorSurname = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(authorID);
        parcel.writeString(authorName);
        parcel.writeString(authorSurname);
    }
}
