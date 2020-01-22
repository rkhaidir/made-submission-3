package com.example.rkhaidir.submission_dua_made.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TvShowDetail implements Parcelable {

    private int id;
    private String title;
    private String release;
    private String overview;
    private String poster;
    private String backdrop;
    private String genres;
    private double rating;

    public TvShowDetail(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String title = object.getString("name");
            String release = object.getString("first_air_date");
            String overview = object.getString("overview");
            String poster = object.getString("poster_path");
            String backdrop = object.getString("backdrop_path");
            double rate = object.getDouble("vote_average");

            JSONArray genres = object.getJSONArray("genres");
            List<String> listGenre = new ArrayList<>();
            for(int i = 0; i < genres.length(); i++) {
                JSONObject jsonObject = genres.getJSONObject(i);
                String genreName = jsonObject.getString("name");
                listGenre.add(genreName);
            }
            String genre = TextUtils.join(", ", listGenre);

            this.id = id;
            this.title = title;
            this.release = release;
            this.overview = overview;
            this.poster = poster;
            this.backdrop = backdrop;
            this.rating = rate;
            this.genres = genre;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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
        dest.writeString(this.overview);
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
        dest.writeString(this.genres);
        dest.writeDouble(this.rating);
    }

    public TvShowDetail() {
    }

    protected TvShowDetail(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.release = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.genres = in.readString();
        this.rating = in.readDouble();
    }

    public static final Parcelable.Creator<TvShowDetail> CREATOR = new Parcelable.Creator<TvShowDetail>() {
        @Override
        public TvShowDetail createFromParcel(Parcel source) {
            return new TvShowDetail(source);
        }

        @Override
        public TvShowDetail[] newArray(int size) {
            return new TvShowDetail[size];
        }
    };
}
