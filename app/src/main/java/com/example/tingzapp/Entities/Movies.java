package com.example.tingzapp.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity
public class Movies implements Parcelable {

    @PrimaryKey
    @NonNull
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("rating")
    private double rating;

    @SerializedName("releaseYear")
    private String releaseYear;

    @SerializedName("genre")
    private ArrayList<String> genre = new ArrayList<>();

    public Movies(String title, String image, double rating, String releaseYear, ArrayList<String> genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre.addAll(genre);
    }

    protected Movies(Parcel in) {
        title = in.readString();
        image = in.readString();
        rating = in.readDouble();
        releaseYear = in.readString();
        genre = in.createStringArrayList();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeDouble(rating);
        parcel.writeString(releaseYear);
        parcel.writeList(genre);

    }
}
