package com.example.tingzapp.Entities;

import java.util.ArrayList;

public class MovieContent {

    public static final ArrayList<Movies> MOVIES = new ArrayList<>();

    public static void clear() {
        MOVIES.clear();
    }

    public static void addMovie(Movies movie) {
        MOVIES.add(movie);
    }

}
