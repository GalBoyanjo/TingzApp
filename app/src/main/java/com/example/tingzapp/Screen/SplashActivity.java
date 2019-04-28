package com.example.tingzapp.Screen;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tingzapp.Database.AppDatabase;
import com.example.tingzapp.Entities.Movies;
import com.example.tingzapp.Interfaces.MoviesRequestCallbacks;
import com.example.tingzapp.Utils.NetworkUtil;
import com.example.tingzapp.R;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    private final static String json_url = "https://api.androidhive.info/json/movies.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        constraintLayout = findViewById(R.id.splash_constraint_layout);
        getMovies();

    }

    private void getMovies() {
        NetworkUtil.getMovies(this, json_url, new MoviesRequestCallbacks() {
            @Override
            public void onSuccess(List<Movies> movies) {

                AppDatabase.getInstance(SplashActivity.this).movieDao().deleteAll();
                AppDatabase.getInstance(SplashActivity.this).movieDao().insertAll(movies);

                Intent intent = new Intent(SplashActivity.this, MoviesList.class);
                startActivity(intent);
            }

            @Override
            public void onError(String errorMessage) {
                Snackbar snackbar = Snackbar
                        .make(constraintLayout, errorMessage, Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMovies();
                            }
                        });

                snackbar.show();

            }
        });
    }
}
