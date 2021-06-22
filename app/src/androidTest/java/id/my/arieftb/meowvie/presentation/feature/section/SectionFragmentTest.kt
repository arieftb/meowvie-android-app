package id.my.arieftb.meowvie.presentation.feature.section

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.SectionType
import id.my.arieftb.meowvie.helper.launchFragmentInHiltContainer
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SectionFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private fun setupIdling() {
        IdlingRegistry.getInstance().register(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @Test
    fun testSectionFragmentOnLoading() {
        launchFragmentInHiltContainer<SectionFragment>(
            fragmentArgs = bundleOf(
                "type" to SectionType.NEW_MOVIE
            )
        )
        onView(withId(R.id.textSectionErrorMessage)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.shimmerSectionDefault)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.listSection)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Thread.sleep(5000)
    }

    @Test
    fun testSectionFragmentOnSuccess() {
        launchFragmentInHiltContainer<SectionFragment>(
            fragmentArgs = bundleOf(
                "type" to SectionType.NEW_MOVIE
            )
        )
        setupIdling()
        onView(withId(R.id.textSectionErrorMessage)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.shimmerSectionDefault)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.listSection)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.listSection)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        Thread.sleep(5000)
    }

    @Test
    fun testSectionFragmentOnEmptyOrError() {
        launchFragmentInHiltContainer<SectionFragment>(
            fragmentArgs = bundleOf(
                "type" to SectionType.UNKNOWN
            )
        )
        setupIdling()
        onView(withId(R.id.textSectionErrorMessage)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.shimmerSectionDefault)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.listSection)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

        Thread.sleep(5000)
    }
}