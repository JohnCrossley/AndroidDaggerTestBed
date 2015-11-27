package com.jccworld.androiddaggertestbed.di.module;

import android.app.Activity;
import android.app.Application;

import com.jccworld.androiddaggertestbed.ActivityScopeFake;
import com.jccworld.androiddaggertestbed.MainActivity;
import com.jccworld.androiddaggertestbed.Foo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johncrossley on 25/11/15.
 */
@Module(
        injects = { MainActivity.class },
        addsTo = ApplicationModule.class
)
public class ActivityModule implements DIModule {

    private final Application application;
    private final Activity activity;

    public ActivityModule(final Activity activity) {
        this.application = (Application) activity.getApplicationContext();
        this.activity = activity;
    }

    @Provides
    public ActivityScopeFake provideActivityScopeFake() {
        return new ActivityScopeFake();
    }

    @Provides
    public Foo provideFoo() {
        return new Foo("production message");
    }
}
