package com.tracking.employeetracking;

import android.support.test.rule.ActivityTestRule;

import com.tracking.employeetracking.activity.LoginPage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Eve on 7/9/17.
 */

public class ActivityLoginTest {

    @Rule
    public ActivityTestRule mAcitvityRule = new ActivityTestRule<>(LoginPage.class);


    @Test
    public void activityLaunch() {

        onView(withText("LoginPage"));
 //       onView(withId(R.layout.activity_login_page)).check(matches(isDisplayed()));
//        onView(withId(R.layout.frag_register)).check(matches(isDisplayed()));
//        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void registerL() {

        onView(withText("Register"));
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));
        onView(withId(R.id.mblInput)).check(matches(isDisplayed()))
            .perform(typeText("677665453"), closeSoftKeyboard());

        onView(withId(R.id.registerBtn)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void signinL() {

    //    onView(withText("Signin")).check(matches(isDisplayed()));
  //      onView(withId(R.id.otp_show)).check(matches(isDisplayed()));
   //     onView(withId(R.id.signinBtn)).perform(click()).check(matches(isDisplayed()));

    }

}
