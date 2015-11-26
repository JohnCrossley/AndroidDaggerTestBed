package com.jccworld.androiddaggertestbed;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jccworld.androiddaggertestbed.di.injector.Injector;
import com.jccworld.androiddaggertestbed.di.injector.ProductionInjector;

import org.w3c.dom.Text;

import javax.inject.Inject;

/**
 * Created by johncrossley on 26/11/15.
 */
public class MainActivity extends Activity {

    private Injector injector;

    @Inject
    ApplicationScopeFake applicationScopeFake;

    @Inject
    ActivityScopeFake activityScopeFake;

    @Inject
    Foo foo;

    public MainActivity() {
        this.injector = new ProductionInjector();
    }

    //only for test
    public void inject(final Injector injector) {
        this.injector = injector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector.inject(this);

        setContentView(R.layout.activity_main);

        TextView outputTextView = (TextView) findViewById(R.id.output);
        outputTextView.setText(foo.getMessage());

        TextView applicationScopeFakeTextView = (TextView) findViewById(R.id.applicationScopedObject);
        applicationScopeFakeTextView.setText(applicationScopeFake.toString());

        TextView activityScopeFakeTextView = (TextView) findViewById(R.id.activityScopedObject);
        activityScopeFakeTextView.setText(activityScopeFake.toString());
    }

}
