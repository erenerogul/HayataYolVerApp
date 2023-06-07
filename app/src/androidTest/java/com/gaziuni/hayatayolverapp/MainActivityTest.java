package com.gaziuni.hayatayolverapp;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
        Intents.init();
    }
    @After
    public void tearDown() {
        Intents.release();
    }
    @Test
    public void checkUserNavigatesToUserActivity() throws InterruptedException {
        onView(ViewMatchers.withId(R.id.textInputEditTextTelefon))
                .perform(typeText("5325931231"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.textInputEditTextSifre))
                .perform(typeText("eren123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.buttonlogin)).perform(click());
        Thread.sleep(2000);
        intended(hasComponent(UserActivity.class.getName()));
    }
    @Test
    public void checkUserNavigatesToAmbulanceActivity() throws InterruptedException {
        onView(ViewMatchers.withId(R.id.textInputEditTextTelefon))
                .perform(typeText("1234567891"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.textInputEditTextSifre))
                .perform(typeText("talha"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.buttonlogin)).perform(click());
        Thread.sleep(2000);
        intended(hasComponent(AmbulanceActivity.class.getName()));
    }
    @Test
    public void testOpenRegisterActivity() throws InterruptedException {
        onView(ViewMatchers.withId(R.id.TextViewUyeOl))
                .perform(click());
        Thread.sleep(2000);
        intended(hasComponent(Register_Activity.class.getName()));
    }
}
