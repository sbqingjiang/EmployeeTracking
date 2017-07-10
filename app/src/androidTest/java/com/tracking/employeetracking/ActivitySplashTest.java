package com.tracking.employeetracking;

import android.support.test.rule.ActivityTestRule;

import com.tracking.employeetracking.activity.HomePage;
import com.tracking.employeetracking.activity.SplashPage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Eve on 7/9/17.
 */

public class ActivitySplashTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            SplashPage.class);

    @Test
    public void checkTitle() {

        onView(withId(R.id.textView)).check(matches(withText("Employee Tracking")));
        onView(withId(R.id.textView)).check(matches(isDisplayed()));

    }
}
