package com.jianke.router.test;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.jianke.router.test.activity.SimpleActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SimpleActivityTest {
    @Rule
    public final IntentsTestRule<SimpleActivity> mRule = new IntentsTestRule<>(SimpleActivity.class);

    @Test
    public void startSimpleActivity() {
        onView(withId(R.id.btn_open)).perform(click());
        Intent resultData = new Intent();
        resultData.putExtra("result", "test in control");
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, resultData);
        Matcher<Intent> intentMatcher = hasComponent("com.jianke.router.test.activity.ResultActivity");
        intending(intentMatcher).respondWith(result);
        onView(withId(R.id.btn_finish)).perform(click());
        intended(intentMatcher);
        onView(withId(R.id.tv_result)).check(matches(withText("test in control")));
    }
}
