package com.jccworld.androiddaggertestbed.di.injector;

import android.app.Activity;

import com.jccworld.androiddaggertestbed.di.module.DIModule;

import dagger.Module;
import dagger.ObjectGraph;

/**
 * Created by johncrossley on 25/11/15.
 */
public class TestInjector implements Injector {

    private final DIModule[] modules;

    public TestInjector(final DIModule... modules) {
        this.modules = modules;
    }

    @Override
    public void inject(final Activity activity) {
        ObjectGraph.create(modules).inject(activity);
    }

    //overload for Services etc
}
