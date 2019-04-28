package com.example.tingzapp.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tingzapp.Entities.Movies;
import com.example.tingzapp.Interfaces.MoviesRequestCallbacks;
import com.example.tingzapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class NetworkUtil {

    public static void getMovies(final Context context, String url, final MoviesRequestCallbacks moviesRequestCallbacks) {

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<Movies> movies = new Gson().fromJson(response, new TypeToken<List<Movies>>() {
                    }.getType());

                    moviesRequestCallbacks.onSuccess(movies);
                } catch (Exception e) {
                    e.printStackTrace();
                    moviesRequestCallbacks.onError(context.getString(R.string.error));
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                moviesRequestCallbacks.onError(context.getString(R.string.error));

            }
        });

        request.setShouldCache(false);
        queue.add(request);

    }

}
