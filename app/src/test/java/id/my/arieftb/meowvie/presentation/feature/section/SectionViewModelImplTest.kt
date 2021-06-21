package id.my.arieftb.meowvie.presentation.feature.section

import androidx.lifecycle.Observer
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingUseCase
import id.my.arieftb.meowvie.helper.applyInstantTaskExecutor
import id.my.arieftb.meowvie.helper.applyTestDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@OptIn(ExperimentalCoroutinesApi::class)
class SectionViewModelImplTest : Spek({
    applyInstantTaskExecutor()
    applyTestDispatcher()

    val getMoviesUseCase: GetMoviesUseCase = mockk(relaxed = true)
    val getTvShowsUseCase: GetTvShowsUseCase = mockk(relaxed = true)
    val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase = mockk(relaxed = true)
    val getTvShowsUpcomingUseCase: GetTvShowsUpcomingUseCase = mockk(relaxed = true)
    val getMoviesPopularUseCase: GetMoviesPopularUseCase = mockk(relaxed = true)
    val getTvShowsPopularUseCase: GetTvShowsPopularUseCase = mockk(relaxed = true)

    val viewModel by memoized {
        SectionViewModelImpl(
            getMoviesUseCase,
            getTvShowsUseCase,
            getMoviesUpcomingUseCase,
            getTvShowsUpcomingUseCase,
            getMoviesPopularUseCase,
            getTvShowsPopularUseCase
        )
    }

    describe(
        "#${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::getMovies.name}"
    ) {
        context(
            "when ${GetMoviesUseCase::class.java.simpleName}.${GetMoviesUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMoviesUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.contentData.observeForever(observer)
                runBlockingTest {
                    viewModel.getMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getMoviesUseCase
                            .invoke()
                    }
                }
            }
        }
        context(
            "when ${GetMoviesUseCase::class.java.simpleName}.${GetMoviesUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = Result.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getMoviesUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.contentData.observeForever(observer)
                runBlockingTest {
                    viewModel.getMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.SUCCESS,
                                data = emptyList()
                            )
                        )
                    }

                    coVerify {
                        getMoviesUseCase
                            .invoke()
                    }
                }
            }
        }
    }

    describe(
        "#${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::getTvShows.name}"
    ) {
        context(
            "when ${GetTvShowsUseCase::class.java.simpleName}.${GetTvShowsUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowsUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.contentData.observeForever(observer)
                runBlockingTest {
                    viewModel.getTvShows()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getTvShowsUseCase
                            .invoke()
                    }
                }
            }
        }
        context(
            "when ${GetTvShowsUseCase::class.java.simpleName}.${GetTvShowsUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = Result.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getTvShowsUseCase.invoke()
                } returns resultDummy
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }
                viewModel.contentData.observeForever(observer)
                runBlockingTest {
                    viewModel.getTvShows()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.SUCCESS,
                                data = emptyList()
                            )
                        )
                    }

                    coVerify {
                        getTvShowsUseCase
                            .invoke()
                    }
                }
            }
        }
    }
})