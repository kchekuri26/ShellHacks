package com.codepath.shellhacks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Information.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InformationDao informationDao();
}
