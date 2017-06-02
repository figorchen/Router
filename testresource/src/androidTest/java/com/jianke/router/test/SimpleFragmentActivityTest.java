package com.jianke.router.test;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import com.jianke.router.test.activity.SimpleFragmentActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SimpleFragmentActivityTest {
    @Rule
    public final ActivityTestRule<SimpleFragmentActivity> mRule = new ActivityTestRule<>(SimpleFragmentActivity.class);
    @Test
    public void startSimpleActivity() {
        onView(withId(R.id.btn_open)).perform(click());
//        Intent resultData = new Intent();
//        resultData.putExtra("result", "test in control");
//        ActivityResult result = new ActivityResult(Activity.RESULT_OK, resultData);
//        Matcher<Intent> intentMatcher = hasComponent(ResultActivity.class.getName());
//        Intents.init();
//        intending(intentMatcher).respondWith(result);
        onView(withId(R.id.btn_finish)).perform(click());
//        registerIdlingResources(new WaitActivityIsResumedIdlingResource(ResultActivity.class.getName()));
//        intended(intentMatcher);
//        Intents.release();
        onView(withId(R.id.tv_result)).check(matches(withText("test in control")));
    }

    private static class WaitActivityIsResumedIdlingResource implements IdlingResource {
        private final ActivityLifecycleMonitor instance;
        private final String activityToWaitClassName;
        private volatile ResourceCallback resourceCallback;
        boolean resumed = false;
        public WaitActivityIsResumedIdlingResource(String activityToWaitClassName) {
            instance = ActivityLifecycleMonitorRegistry.getInstance();
            this.activityToWaitClassName = activityToWaitClassName;
        }

        @Override
        public String getName() {
            return this.getClass().getName();
        }

        @Override
        public boolean isIdleNow() {
            resumed = isActivityLaunched();
            if(resumed && resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }

            return resumed;
        }

        private boolean isActivityLaunched() {
            Collection<Activity> activitiesInStage = instance.getActivitiesInStage(Stage.RESUMED);
            for (Activity activity : activitiesInStage) {
                if(activity.getClass().getName().equals(activityToWaitClassName)){
                    return true;
                }
            }
            return false;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }
    }
}
