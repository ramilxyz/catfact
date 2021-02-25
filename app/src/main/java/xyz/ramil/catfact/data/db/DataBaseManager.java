package xyz.ramil.catfact.data.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import xyz.ramil.catfact.data.model.CatFactModel;

public class DataBaseManager {

    AppDatabase appDatabase;

    LiveData<List<CatFactModel>>  data;

    CatFactModel catFactModel;

    AppDatabase initializeDB(Context context) {
        return AppDatabase.appDatabase.getAppDatabase(context);
    }

    void insertData(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
        //вывести в рабочий поток
            appDatabase.catFactDao().insertData(catFactModel);

    }

    LiveData<List<CatFactModel>> getData(Context context) {
        appDatabase = initializeDB(context);
        data = appDatabase.catFactDao().getAll();
        return data;
    }

    void delete(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
        appDatabase.catFactDao().delete(catFactModel);
    }
}
