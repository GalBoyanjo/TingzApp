package com.example.tingzapp.Screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tingzapp.Database.AppDatabase;
import com.example.tingzapp.Utils.DetailsActivity;
import com.example.tingzapp.Entities.Movies;
import com.example.tingzapp.Interfaces.MovieItemCallback;
import com.example.tingzapp.Entities.MovieContent;
import com.example.tingzapp.Utils.MoviesAdapter;
import com.example.tingzapp.R;

import java.util.List;

public class MoviesList extends AppCompatActivity implements MovieItemCallback {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        recyclerView = findViewById(R.id.movies_recycle_view);
        progressBar = findViewById(R.id.main_progress);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMovies();
    }

    private void loadMovies() {
        MovieContent.clear();
        List<Movies> cachedMovies = AppDatabase.getInstance(this).movieDao().getAll();
        MovieContent.MOVIES.addAll(cachedMovies);
        adapter = new MoviesAdapter(MovieContent.MOVIES, MoviesList.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void movieSelected(int position) {
        if (position < 0 || position >= MovieContent.MOVIES.size()) return;

        Intent intent = new Intent(MoviesList.this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_ITEM_POSITION, position);
        startActivity(intent);

    }


}
