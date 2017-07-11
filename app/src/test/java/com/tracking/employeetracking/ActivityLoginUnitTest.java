package com.tracking.employeetracking;


import android.support.test.filters.SmallTest;

import com.tracking.employeetracking.activity.LoginPage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Eve on 7/11/17.
 */

@RunWith(JUnit4.class)
@SmallTest
public class ActivityLoginUnitTest {

    private LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    public void seeTT() {
        boolean resultSee = loginPage.tteesstt(2);
        assertThat(resultSee, is(equalTo(false)));
    }
}
