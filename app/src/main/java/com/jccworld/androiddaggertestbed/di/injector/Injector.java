package com.jccworld.androiddaggertestbed.di.injector;

import android.app.Activity;

/**
 * Created by johncrossley on 26/11/15.
 */
public interface Injector {
    void inject(final Activity activity);
    //overloads for Services, Fragments, Views, etc
}
