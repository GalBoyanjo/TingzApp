package com.example.tingzapp.Interfaces;

import com.example.tingzapp.Entities.Movies;

import java.util.List;

public interface MoviesRequestCallbacks {

    void onSuccess(List<Movies> heroes);
    void onError(String errorMessage);
}
