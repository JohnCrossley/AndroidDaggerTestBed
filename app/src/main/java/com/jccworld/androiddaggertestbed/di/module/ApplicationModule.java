package com.jccworld.androiddaggertestbed.di.module;

import com.jccworld.androiddaggertestbed.ApplicationScopeFake;
import com.jccworld.androiddaggertestbed.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johncrossley on 25/11/15.
 */
@Module(
        injects = { Application.class },
        library = true
)
public class ApplicationModule implements DIModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    public ApplicationScopeFake provideApplicationScopeFake() {
        return new ApplicationScopeFake();
    }

}
