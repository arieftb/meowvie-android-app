package id.my.arieftb.meowvie.presentation.feature.explore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.helper.launchFragmentInHiltContainer
import id.my.arieftb.meowvie.presentation.adapter.ContentPortraitGridRecyclerAdapter
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExploreFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @Test
    fun testExploreFragmentOpenInitiation() {
        launchFragmentInHiltContainer<ExploreFragment>()
        onView(withId(R.id.formExploreSearch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.buttonExploreSearch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.listExplore)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.textExploreErrorMessage)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.shimmerExploreDefault)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Thread.sleep(3000)
    }

    @Test
    fun testExploreFragmentNotFoundSearching() {
        launchFragmentInHiltContainer<ExploreFragment>()
        onView(withId(R.id.formExploreSearch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.formExploreSearch)).perform(click())
        onView(withId(R.id.formExploreSearch)).perform(typeText("afpjwfjwfjawpifjap"))
        onView(withId(R.id.buttonExploreSearch)).perform(click())
        onView(withId(R.id.listExplore)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.textExploreErrorMessage)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Thread.sleep(3000)
    }

    @Test
    fun testExploreFragmentFoundSearching() {
        launchFragmentInHiltContainer<ExploreFragment>()
        onView(withId(R.id.formExploreSearch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.formExploreSearch)).perform(click())
        onView(withId(R.id.formExploreSearch)).perform(typeText("avenger"))
        onView(withId(R.id.buttonExploreSearch)).perform(click())
        onView(withId(R.id.textExploreErrorMessage)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.listExplore)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.listExplore)).perform(actionOnItemAtPosition<ContentPortraitGridRecyclerAdapter.ContentPortraitGridRecyclerViewHolder>(6, scrollTo()))
        Thread.sleep(3000)
    }
}