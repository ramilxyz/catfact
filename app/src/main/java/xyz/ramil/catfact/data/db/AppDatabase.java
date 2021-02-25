package xyz.ramil.catfact.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import xyz.ramil.catfact.BaseApp;

@Database(entities = {CatFactDao.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CatFactDao catFactDao();

    public static AppDatabase appDatabase;

    AppDatabase getAppDatabase (Context context) {

        if(appDatabase == null)
           appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "database-name").build();

        return appDatabase;
    }



}