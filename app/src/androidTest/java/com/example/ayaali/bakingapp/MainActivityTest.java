package com.example.ayaali.bakingapp;

import android.support.test.espresso.matcher.CursorMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ayaali.bakingapp.activities.MainActivity;
import com.example.ayaali.bakingapp.activities.PlayerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by AyaAli on 02/06/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void GridViewTest()
    {
        onData(
                is(instanceOf(String.class))
        );
    }

}
