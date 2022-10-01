package com.example.partnersincode.optimedia.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SeriesLog implements Parcelable {
    private int SL_ID, seriesID;
    private String season, episode,s_note,s_timestamp;

    public SeriesLog() {
    }

    public SeriesLog(int seriesID, String season, String episode, String s_note, String s_timestamp) {
        this.seriesID = seriesID;
        this.season = season;
        this.episode = episode;
        this.s_note = s_note;
        this.s_timestamp = s_timestamp;
    }

    protected SeriesLog(Parcel in) {
        SL_ID = in.readInt();
        seriesID = in.readInt();
        season = in.readString();
        episode = in.readString();
        s_note = in.readString();
        s_timestamp = in.readString();
    }

    public static final Creator<SeriesLog> CREATOR = new Creator<SeriesLog>() {
        @Override
        public SeriesLog createFromParcel(Parcel in) {
            return new SeriesLog(in);
        }

        @Override
        public SeriesLog[] newArray(int size) {
            return new SeriesLog[size];
        }
    };

    public int getSL_ID() {
        return SL_ID;
    }

    public void setSL_ID(int SL_ID) {
        this.SL_ID = SL_ID;
    }

    public int getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getS_note() {
        return s_note;
    }

    public void setS_note(String s_note) {
        this.s_note = s_note;
    }

    public String getS_timestamp() {
        return s_timestamp;
    }

    public void setS_timestamp(String s_timestamp) {
        this.s_timestamp = s_timestamp;
    }

    @Override
    public String toString() {
        return "SeriesLog: " + seriesID +", " + season + ", " + episode ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(SL_ID);
        parcel.writeInt(seriesID);
        parcel.writeString(season);
        parcel.writeString(episode);
        parcel.writeString(s_note);
        parcel.writeString(s_timestamp);
    }
}
