package com.jccworld.androiddaggertestbed.di.injector;

import android.app.Activity;

import com.jccworld.androiddaggertestbed.Application;
import com.jccworld.androiddaggertestbed.di.module.ActivityModule;

/**
 * Created by johncrossley on 25/11/15.
 */
public class ProductionInjector implements Injector {

    @Override
    public void inject(final Activity activity) {
        getApplication(activity)
                .getObjectGraph()
                .plus(new ActivityModule(activity))
                .inject(activity);
    }

    //overload for Services etc

    private static Application getApplication(final Activity activity) {
        if (activity.getApplication() instanceof Application) {
            return (Application) activity.getApplication();
        } else {
            throw new RuntimeException("Application must extend "  + Application.class.getName());
        }
    }
}
