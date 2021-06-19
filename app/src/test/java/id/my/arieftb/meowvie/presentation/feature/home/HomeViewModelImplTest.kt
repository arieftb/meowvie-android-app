package id.my.arieftb.meowvie.presentation.feature.home

import androidx.lifecycle.Observer
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.highlight.GetMoviesHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.highlight.GetTvShowsHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingHighlightUseCase
import id.my.arieftb.meowvie.helper.applyInstantTaskExecutor
import id.my.arieftb.meowvie.helper.applyTestDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.lang.Exception

class HomeViewModelImplTest : Spek({
    applyTestDispatcher()
    applyInstantTaskExecutor()

    val getMoviesHighlightUseCase: GetMoviesHighlightUseCase = mockk(relaxed = true)
    val getTvShowsHighlightUseCase: GetTvShowsHighlightUseCase = mockk(relaxed = true)
    val getMoviesUpcomingUseCase: GetMoviesUpcomingHighlightUseCase = mockk(relaxed = true)
    val getTvShowsUpcomingHighlightUseCase: GetTvShowsUpcomingHighlightUseCase =
        mockk(relaxed = true)
    val getMoviesPopularHighlightUseCase: GetMoviesPopularHighlightUseCase = mockk(relaxed = true)
    val getTvShowsPopularHighlightUseCase: GetTvShowsPopularHighlightUseCase = mockk(relaxed = true)

    val viewModel by memoized {
        HomeViewModelImpl(
            getMoviesHighlightUseCase,
            getTvShowsHighlightUseCase,
            getMoviesUpcomingUseCase,
            getTvShowsUpcomingHighlightUseCase,
            getMoviesPopularHighlightUseCase,
            getTvShowsPopularHighlightUseCase
        )
    }

    describe(
        "#${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::getMoviesHighlight.name}"
    ) {
        context(
            "when ${GetMoviesHighlightUseCase::class.java.simpleName}.${GetMoviesHighlightUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMoviesHighlightUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::moviesData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.moviesData.observeForever(observer)
                viewModel.getMoviesHighlight()

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    getMoviesHighlightUseCase.invoke()
                }
            }
        }
        context(
            "when ${GetMoviesHighlightUseCase::class.java.simpleName}.${GetMoviesHighlightUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = Result.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getMoviesHighlightUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::moviesData.name} should has Data Status Loading and Success sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.moviesData.observeForever(observer)
                viewModel.getMoviesHighlight()

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
                }

                coVerify {
                    getMoviesHighlightUseCase.invoke()
                }
            }
        }
    }
    describe(
        "#${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::getTvShowsHighlight.name}"
    ) {
        context(
            "when ${GetTvShowsHighlightUseCase::class.java.simpleName}.${GetTvShowsHighlightUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowsHighlightUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::tvShowsData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.tvShowsData.observeForever(observer)
                viewModel.getTvShowsHighlight()

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    getTvShowsHighlightUseCase.invoke()
                }
            }
        }
        context(
            "when ${GetTvShowsHighlightUseCase::class.java.simpleName}.${GetTvShowsHighlightUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = Result.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getTvShowsHighlightUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::tvShowsData.name} should has Data Status Loading and Success sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.tvShowsData.observeForever(observer)
                viewModel.getTvShowsHighlight()

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
                }

                coVerify {
                    getTvShowsHighlightUseCase.invoke()
                }
            }
        }
    }
})