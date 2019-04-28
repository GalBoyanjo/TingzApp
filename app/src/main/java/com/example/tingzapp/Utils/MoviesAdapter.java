package com.example.tingzapp.Utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tingzapp.Entities.Movies;
import com.example.tingzapp.Interfaces.MovieItemCallback;
import com.example.tingzapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private MovieItemCallback movieItemCallback;
    private List<Movies> moviesData;

    private Picasso picasso;

    public MoviesAdapter(List<Movies> moviesData, MovieItemCallback movieItemCallback) {
        this.moviesData = moviesData;
        this.movieItemCallback = movieItemCallback;
        this.picasso = Picasso.get();
    }

    public void setData(List<Movies> items){
        moviesData.clear();
        moviesData.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(moviesData.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView ivImage;
        public final TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.row_movie_iv);
            tvTitle = view.findViewById(R.id.row_movie_title_tv);
            view.setOnClickListener(this);
        }

        public void bind(Movies movies) {
            picasso.load(movies.getImage())
                    .into(ivImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.i("onSuccess", "onSuccess");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("onError", "onError() called with: e = [" + e + "]");
                        }
                    });
            tvTitle.setText(movies.getTitle());
        }

        @Override
        public void onClick(View view) {
            if (movieItemCallback == null) return;
            movieItemCallback.movieSelected(getAdapterPosition());
        }
    }
}
