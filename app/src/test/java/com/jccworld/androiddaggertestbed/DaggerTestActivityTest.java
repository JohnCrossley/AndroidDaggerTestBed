package com.jccworld.androiddaggertestbed;

import android.widget.TextView;

import com.jccworld.androiddaggertestbed.di.injector.Injector;
import com.jccworld.androiddaggertestbed.di.injector.ProductionInjector;
import com.jccworld.androiddaggertestbed.di.injector.TestInjector;
import com.jccworld.androiddaggertestbed.di.module.DIModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import dagger.Module;
import dagger.Provides;

import static org.junit.Assert.assertEquals;

/**
 * Created by johncrossley on 26/11/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = RobolectricTestSettings.SDK_VERSION, manifest = RobolectricTestSettings.MANIFEST_PATH)
public class DaggerTestActivityTest {
    private static final String DEBUG_MESSAGE = "debug message";
    private static final String PRODUCTION_MESSAGE = "production message";

    private ActivityController<MainActivity> sutController;
    private MainActivity sut;

    //could be real mocks...
    private ApplicationScopeFake fakeApplicationScopeFake;
    private ActivityScopeFake fakeActivityScopeFake;
    private Foo fakeFoo;

    @Before
    public void setUp() {
        fakeApplicationScopeFake = new ApplicationScopeFake();
        fakeActivityScopeFake = new ActivityScopeFake();
        fakeFoo = new Foo(DEBUG_MESSAGE);
    }

    private void createActivity(final Injector injector) {
        sutController = ActivityController.of(Robolectric.getShadowsAdapter(), new MainActivity(injector));
        sut = sutController.get();
    }

    private void activityCallOnCreateOnStartOnResume() {
        sutController.create();
        sutController.start();
        sutController.resume();
    }

    /**
     * Test injected with test module.  Can be mocks, stubs, fakes, etc.
     */
    @Test
    public void outputTextViewDisplaysDefaultText() {
        // init
        createActivity(new TestInjector(new TestModule()));

        // run
        activityCallOnCreateOnStartOnResume();

        // post-run init
        TextView outputTextView = (TextView) sut.findViewById(R.id.output);

        // verify
        assertEquals(DEBUG_MESSAGE, outputTextView.getText());
    }

    /**
     * Test injected with production module ("real")
     */
    @Test
    public void outputTextViewDisplaysCorrectProductionText() {
        // init
        createActivity(new ProductionInjector());

        // run
        activityCallOnCreateOnStartOnResume();

        // post-run init
        TextView outputTextView = (TextView) sut.findViewById(R.id.output);

        // verify
        assertEquals(PRODUCTION_MESSAGE, outputTextView.getText());
    }

    @Module(injects = MainActivity.class)
    class TestModule implements DIModule {
        @Provides
        public ApplicationScopeFake provideApplicationScopeFake() {
            return fakeApplicationScopeFake;
        }

        @Provides
        public ActivityScopeFake provideActivityScopeFake() {
            return fakeActivityScopeFake;
        }

        @Provides
        public Foo provideFoo() {
            return fakeFoo;
        }
    }
}
