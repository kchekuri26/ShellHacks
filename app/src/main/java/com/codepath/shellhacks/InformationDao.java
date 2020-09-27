package com.codepath.shellhacks;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InformationDao {

    @Query("SELECT * FROM information")
    List<Information> getAll();

    @Insert
    void insertAll(Information... informations);

    @Delete
    void delete(Information information);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Information information);
}
