package com.example.rkhaidir.submission_dua_made.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShow implements Parcelable {

    private int id;
    private String title;
    private String release;
    private String backdrop;
    private double rate;

    public TvShow(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String title = object.getString("name");
            String release = object.getString("first_air_date");
            String backdrop = object.getString("backdrop_path");
            Double rate = object.getDouble("vote_average");

            this.id = id;
            this.title = title;
            this.release = release;
            this.backdrop = backdrop;
            this.rate = rate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.release);
        dest.writeString(this.backdrop);
        dest.writeDouble(this.rate);
    }

    public TvShow() {
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.release = in.readString();
        this.backdrop = in.readString();
        this.rate = in.readDouble();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
