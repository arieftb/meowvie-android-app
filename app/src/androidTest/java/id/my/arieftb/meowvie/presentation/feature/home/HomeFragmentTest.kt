package id.my.arieftb.meowvie.presentation.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.helper.launchFragmentInHiltContainer
import id.my.arieftb.meowvie.presentation.adapter.ContentPortraitRecyclerAdapter
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        launchFragmentInHiltContainer<HomeFragment>()
        IdlingRegistry.getInstance().register(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @Test
    fun testHomeFragmentOpenLoadDataShow() {
        onView(withId(R.id.sectionHomeNewMovie)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(
            allOf(
                withId(R.id.shimmerContentSectionDefault),
                isDescendantOfA(withId(R.id.sectionHomeNewMovie))
            )
        ).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(
            allOf(
                withId(R.id.textContentSectionErrorMessage),
                isDescendantOfA(withId(R.id.sectionHomeNewMovie))
            )
        ).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(
            allOf(
                withId(R.id.listContentSection),
                isDescendantOfA(withId(R.id.sectionHomeNewMovie))
            )
        ).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(
            allOf(
                withId(R.id.listContentSection),
                isDescendantOfA(withId(R.id.sectionHomeNewMovie))
            )
        ).perform(
            actionOnItemAtPosition<ContentPortraitRecyclerAdapter.MoviesRecyclerViewHolder>(
                3,
                ViewActions.scrollTo()
            )
        )
    }
}