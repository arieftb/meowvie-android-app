package id.my.arieftb.meowvie.presentation.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.helper.launchFragmentInHiltContainer
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(IdlingResourceHelper.getEspressoIdlingResource())
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHelper.getEspressoIdlingResource())
    }

    @Test
    fun testDetailFragmentOpenDetailNotFound() {
        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs = bundleOf("id" to 11111111L, "type" to ContentType.MOVIE)
        )
        onView(withId(R.id.textDetailErrorMessage)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.groupDetailView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.shimmerDetailLoading)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun testDetailFragmentOpenDetailFoundMovie() {
        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs = bundleOf("id" to 337404L, "type" to ContentType.MOVIE)
        )
        onView(withId(R.id.textDetailErrorMessage)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.shimmerDetailLoading)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.imageDetailPoster)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.imageDetailType)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailTitle)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailGenre)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.ratingDetailVote)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailReleaseDate)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.buttonDetailFavorite)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.buttonDetailShare)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailOverviewLabel)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailOverview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.imageDetailType)).check(matches(withTagValue(equalTo(R.drawable.ic_content_movie))))
    }

    @Test
    fun testDetailFragmentOpenDetailFoundTV() {
        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs = bundleOf("id" to 84958L, "type" to ContentType.TV)
        )
        onView(withId(R.id.textDetailErrorMessage)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.shimmerDetailLoading)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.imageDetailPoster)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.imageDetailType)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailTitle)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailGenre)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.ratingDetailVote)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailReleaseDate)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.buttonDetailFavorite)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.buttonDetailShare)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailOverviewLabel)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textDetailOverview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.imageDetailType)).check(matches(withTagValue(equalTo(R.drawable.ic_content_tv))))
    }
}