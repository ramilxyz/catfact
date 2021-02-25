package xyz.ramil.catfact;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpPresenter;
import xyz.ramil.catfact.data.ApiManager;
import xyz.ramil.catfact.model.Facts;

public class MainPresenter extends MvpPresenter<MainView> {

    public void loadData() {
        new ApiManager().getFact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Facts>() {
                    @Override
                    public void onSuccess(Facts model) {
                        getViewState().update(model);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("LoadFacts::Throwable", e.getLocalizedMessage());
                    }
                });
    }
}
