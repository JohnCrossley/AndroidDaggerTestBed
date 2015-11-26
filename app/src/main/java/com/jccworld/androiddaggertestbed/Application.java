package com.jccworld.androiddaggertestbed;

import com.jccworld.androiddaggertestbed.di.module.ApplicationModule;

import dagger.ObjectGraph;

/**
 * Created by johncrossley on 25/11/15.
 */
public class Application extends android.app.Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new ApplicationModule(this));
        objectGraph.inject(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
