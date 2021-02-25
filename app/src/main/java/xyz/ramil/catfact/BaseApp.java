package xyz.ramil.catfact;

import android.app.Application;

import dagger.Module;

@Module
public class BaseApp extends Application {

    private static BaseApp sInstance;
    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static BaseApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sAppComponent = buildComponent();
        sAppComponent.inject(this);
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent
                .builder()
                .application((BaseApp) getApplicationContext())
                .build();
    }
}
