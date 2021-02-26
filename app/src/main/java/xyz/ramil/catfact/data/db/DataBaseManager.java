package xyz.ramil.catfact.data.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

import rx.schedulers.Schedulers;
import xyz.ramil.catfact.data.model.CatFactModel;

public class DataBaseManager {

    AppDatabase appDatabase;

    LiveData<List<CatFactModel>>  data;

    CatFactModel catFactModel;

     AppDatabase initializeDB(Context context) {
        return AppDatabase.getAppDatabase(context);
    }

  public   void insertData(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
              appDatabase.catFactDao().insertData(catFactModel);
    }

    public  LiveData<List<CatFactModel>> getData(Context context) {
        appDatabase = initializeDB(context);
        data = appDatabase.catFactDao().getAll();
        return data;
    }

    public void delete(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
        Thread thread =   new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.catFactDao().delete(catFactModel);
            }});
        thread.start();
    }
}
