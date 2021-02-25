package xyz.ramil.catfact;

import android.content.Context;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpPresenter;
import xyz.ramil.catfact.data.ApiManager;
import xyz.ramil.catfact.data.db.DataBaseManager;
import xyz.ramil.catfact.data.model.CatFactModel;
import xyz.ramil.catfact.model.Facts;

public class MainPresenter extends MvpPresenter<MainView> {

    DataBaseManager dataBaseManager = new DataBaseManager();

    public void loadData(Context context) {
        new ApiManager().getFact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Facts>() {
                    @Override
                    public void onSuccess(Facts model) {


                        CatFactModel catFactModel = new CatFactModel();
                        catFactModel.cat = new byte[] {0};
                        catFactModel.fact = model.getText();
                        catFactModel.type = model.getType();

                        dataBaseManager.insertData(context, catFactModel);

                        Log.d("SSSS", ""+dataBaseManager.getData(context).getValue())







//                                Glide.with(context)
//                                .load("https://cataas.com/cat")
//                                .circleCrop()
//                                .signature(new ObjectKey(System.currentTimeMillis()))
//                                .into(holder.imageView);
                        ;

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LoadFacts::Throwable", e.getLocalizedMessage());
                    }
                });
    }
}
