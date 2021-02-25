package xyz.ramil.catfact;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component
public interface AppComponent {
    void inject(BaseApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApp app);

        AppComponent build();
    }
}