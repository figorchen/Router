package com.jianke.router;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CallerTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void equalsTo() throws Exception {
        Context context = new Application();
        Assert.assertEquals(Caller.getInstance(context).equalsTo(context), true);
    }

    @Test
    public void equalsTo1() throws Exception {
        Activity activity = new Activity();
        Assert.assertEquals(Caller.getInstance(activity).equalsTo(activity), true);
    }

    @Test
    public void equalsTo2() throws Exception {
        Fragment fragment = new Fragment();
        Assert.assertEquals(Caller.getInstance(fragment).equalsTo(fragment), true);
    }
}