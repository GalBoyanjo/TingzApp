package com.example.tingzapp.Screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tingzapp.Entities.Movies;
import com.example.tingzapp.R;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment{

    private static final String ARG_MOVIE = "Movies";

    private Movies movies;

    private ImageView detailImg;
    private TextView movieName;
    private TextView movieReDate;
    private TextView movieRate;
    private TextView movieGenre;

    private Picasso picasso;

    public MovieDetailsFragment() { }

    public static MovieDetailsFragment newInstance(Movies movies) {

        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_MOVIE, movies);
        movieDetailsFragment.setArguments(bundle);


        return movieDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picasso = Picasso.get();
        if (getArguments() != null) {
            movies = getArguments().getParcelable(ARG_MOVIE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_movie_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailImg = view.findViewById(R.id.detail_img);
        movieName = view.findViewById(R.id.movie_name);
        movieReDate = view.findViewById(R.id.movie_date);
        movieRate = view.findViewById(R.id.movie_rated);
        movieGenre =  view.findViewById(R.id.movie_genre_list);

        setMovie();
    }

    private void setMovie(){
        if(movies == null) return;

        picasso.load(movies.getImage()).into(detailImg);

        movieName.setText(movies.getTitle());
        movieReDate.setText(movies.getReleaseYear());
        movieRate.setText(String.valueOf(movies.getRating()));
        movieReDate.setText(movies.getReleaseYear());

        String tempString = "";
        for(int i =0; i<movies.getGenre().size(); i++) {
            if (i == 0)
                tempString = (movies.getGenre().get(i));
            else
                tempString = (tempString + ", " + movies.getGenre().get(i));
        }
        movieGenre.setText(tempString);

    }



}
