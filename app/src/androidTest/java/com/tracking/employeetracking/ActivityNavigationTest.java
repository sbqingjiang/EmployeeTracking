package com.tracking.employeetracking;

import android.support.test.rule.ActivityTestRule;

import com.tracking.employeetracking.activity.HomePage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by yinqingjiang on 7/9/17.
 */

public class ActivityNavigationTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            HomePage.class);

    @Test
    public void activityLaunch() {

      //  onView(withId(R.id.toolbar)).perform(click());
      //  onView(withId(R.id.circleImageView)).check(matches(isDisplayed()));

        onView(withId(R.id.show_shift)).check(matches(withText("Next Shift")));
    }
    @Test
    public void testNavigationDrawerItemClick() {
        onView(withId(R.id.drawer_layout)).perform(open());
        //openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.home_container));
    }
}
