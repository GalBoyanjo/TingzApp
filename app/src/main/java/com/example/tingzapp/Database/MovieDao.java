package com.example.tingzapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.tingzapp.Entities.Movies;

import java.util.Collection;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movies ORDER BY releaseYear DESC")
    List<Movies> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(Collection<Movies> movies);

    @Query("DELETE FROM Movies")
    void deleteAll();
}
