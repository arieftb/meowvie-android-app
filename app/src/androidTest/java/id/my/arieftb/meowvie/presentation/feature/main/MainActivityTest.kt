package id.my.arieftb.meowvie.presentation.feature.main

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.helper.IdlingResourceHelper
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @Test
    fun testMainActivityOpenInitiation() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.app_name
                    )
                )
            )
        )
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomNavigationMain)).check(matches(isDisplayed()))
        onView(withId(R.id.homeFragment)).check(matches(isSelected()))
    }

    @Test
    fun testMainActivityOpenExploreFragmentFromHome() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.app_name
                    )
                )
            )
        )
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomNavigationMain)).check(matches(isDisplayed()))
        onView(withId(R.id.homeFragment)).check(matches(isSelected()))
        onView(withId(R.id.exploreFragment)).perform(click())
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.menu_explore
                    )
                )
            )
        )
        onView(withId(R.id.exploreFragment)).check(matches(isSelected()))
    }

    @Test
    fun testMainActivityOpenFavoriteFragmentFromHome() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.app_name
                    )
                )
            )
        )
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomNavigationMain)).check(matches(isDisplayed()))
        onView(withId(R.id.homeFragment)).check(matches(isSelected()))
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.menu_watchlist
                    )
                )
            )
        )
        onView(withId(R.id.favoriteFragment)).check(matches(isSelected()))
    }
    @Test
    fun testMainActivityOpenAccountFragmentFromHome() {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.app_name
                    )
                )
            )
        )
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomNavigationMain)).check(matches(isDisplayed()))
        onView(withId(R.id.homeFragment)).check(matches(isSelected()))
        onView(withId(R.id.accountFragment)).perform(click())
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(
            matches(
                withText(
                    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                        R.string.menu_account
                    )
                )
            )
        )
        onView(withId(R.id.accountFragment)).check(matches(isSelected()))
    }
}