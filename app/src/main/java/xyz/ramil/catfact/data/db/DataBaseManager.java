package xyz.ramil.catfact.data.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

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
        //вывести в рабочий поток


      Executors.newSingleThreadExecutor().execute(new Runnable() {
          @Override
          public void run() {
              appDatabase.catFactDao().insertData(catFactModel);
          }
      });



    }

    public  LiveData<List<CatFactModel>> getData(Context context) {
        appDatabase = initializeDB(context);
        data = appDatabase.catFactDao().getAll();
        return data;
    }

    public void delete(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
        appDatabase.catFactDao().delete(catFactModel);
    }
}
