package com.tracking.employeetracking;

import android.support.test.rule.ActivityTestRule;

import com.tracking.employeetracking.activity.HomePage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by yinqingjiang on 7/9/17.
 */

public class ActivityNavigationTest {
    // test homepage UI
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            HomePage.class);

    // test map views show correctly
    @Test
    public void mapView() {

        //  onView(withId(R.id.circleImageView)).check(matches(isDisplayed()));
        onView(withId(R.id.show_shift)).check(matches(withText("Next Shift")));
        onView(withId(R.id.shift_date)).check(matches(isDisplayed()));
        onView(withId(R.id.next_shift)).check(matches(isDisplayed()));
        onView(withId(R.id.my_map)).check(matches(isDisplayed()));
        onView(withText("Also working")).check(matches(isDisplayed()));
        onView(withId(R.id.circle1)).check(matches(isDisplayed()));
        onView(withId(R.id.circle2)).check(matches(isDisplayed()));
        onView(withId(R.id.circle3)).check(matches(isDisplayed()));
        onView(withId(R.id.circle4)).check(matches(isDisplayed()));
    }
    //test dialog view
    @Test
    public void dialogDispaly()
    {
        onView(withId(R.id.linearLayout)).perform(click());
        onView(withText("Today's shift :12:00 pm - 8:00 pm \n at Chicago")).check(matches(isDisplayed()));
        onView(withText("Notes:")).check(matches(isDisplayed()));
        onView(withId(R.id.editText)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel)).check(matches(isDisplayed()));
        onView(withId(R.id.checkin)).check(matches(isDisplayed()));
        onView(withId(R.id.dialog_title)).check(matches(isDisplayed()));
    }
    // test dialog button click
    @Test
    public void dialogButtonClick()
    {
        onView(withId(R.id.linearLayout)).perform(click());
        onView(withId(R.id.cancel)).perform(click());
        onView(withId(R.id.linearLayout)).perform(click());
        onView(withId(R.id.checkin)).perform(click());

    }
    //schdule
    @Test
    public void testNavigationDrawerScheduleClick() {
        onView(withId(R.id.drawer_layout)).perform(open());
        onView(withText("Schedule"))
                .perform(click());
        onView(withId(R.layout.frag_host_myshifts)).check(matches(isDisplayed()));
        //openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.home_container));
    }
    //dashboard
    @Test
    public void profile() {
        onView(withId(R.id.drawer_layout)).perform(open());
        onView(withText("Profile"))
                .perform(click());
        onView(withId(R.id.user_image)).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        onView(withId(R.id.Phone)).check(matches(isDisplayed()));
        onView(withId(R.id.textView1)).check(matches(isDisplayed()));
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_log_out)).check(matches(isDisplayed()));
    }
    @Test
    public void logout()
    {
        onView(withId(R.id.drawer_layout)).perform(open());
        onView(withText("Logout"))
                .perform(click());
        onView(withId(R.id.registerBtn)).check(matches(isDisplayed()));
    }
}
