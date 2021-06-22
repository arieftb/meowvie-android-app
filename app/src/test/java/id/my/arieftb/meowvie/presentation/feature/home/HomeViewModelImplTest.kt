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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.meta.Ignore
import org.spekframework.spek2.style.specification.describe

@OptIn(ExperimentalCoroutinesApi::class)
@Ignore
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
                runBlockingTest {
                    viewModel.getMoviesHighlight()

                    coVerify {
                        observer.onChanged(Data(Status.LOADING))
                    }

                    coVerify {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getMoviesHighlightUseCase.invoke()
                    }

                    viewModel.moviesData.removeObserver(observer)
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
                runBlockingTest {
                    viewModel.getMoviesHighlight()

                    coVerify {
                        observer.onChanged(Data(Status.LOADING))
                    }
                    coVerify {
                        observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
                    }

                    coVerify {
                        getMoviesHighlightUseCase.invoke()
                    }

                    viewModel.moviesData.removeObserver(observer)
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
                runBlockingTest {
                    viewModel.getTvShowsHighlight()

                    coVerify {
                        observer.onChanged(Data(Status.LOADING))
                    }
                    coVerify {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }


                    coVerify {
                        getTvShowsHighlightUseCase.invoke()
                    }

                    viewModel.tvShowsData.removeObserver(observer)
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

                runBlockingTest {
                    viewModel.getTvShowsHighlight()

                    coVerify {
                        observer.onChanged(Data(Status.LOADING))
                    }

                    coVerify {
                        observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
                    }

                    coVerify {
                        getTvShowsHighlightUseCase.invoke()
                    }
                }

                viewModel.tvShowsData.removeObserver(observer)
            }
        }
    }
//    describe(
//        "#${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::getMoviesUpcomingHighlight.name}"
//    ) {
//        context(
//            "when ${GetMoviesUpcomingHighlightUseCase::class.java.simpleName}.${GetMoviesUpcomingHighlightUseCase::invoke.name} return Result Failure"
//        ) {
//            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
//            beforeEachGroup {
//                coEvery {
//                    getMoviesUpcomingUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::moviesUpcomingData.name} should has Data Status Loading and Error sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.moviesUpcomingData.observeForever(observer)
//                runBlockingTest {
//                    viewModel.getMoviesUpcomingHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(
//                            Data(
//                                Status.ERROR,
//                                errorMessage = "Something went wrong"
//                            )
//                        )
//                    }
//
//                    coVerify {
//                        getMoviesUpcomingUseCase.invoke()
//                    }
//                }
//            }
//        }
//        context(
//            "when ${GetMoviesUpcomingHighlightUseCase::class.java.simpleName}.${GetMoviesUpcomingHighlightUseCase::invoke.name} return Result Success"
//        ) {
//            val resultDummy = Result.Success<List<Content>>(data = emptyList())
//            beforeEachGroup {
//                coEvery {
//                    getMoviesUpcomingUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::moviesUpcomingData.name} should has Data Status Loading and Success sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.moviesUpcomingData.observeForever(observer)
//                runBlockingTest {
//                    viewModel.getMoviesUpcomingHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
//                    }
//
//                    coVerify {
//                        getMoviesUpcomingUseCase.invoke()
//                    }
//                }
//            }
//        }
//    }
//    describe(
//        "#${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::getTvShowsUpcomingHighlight.name}"
//    ) {
//        context(
//            "when ${GetTvShowsUpcomingHighlightUseCase::class.java.simpleName}.${GetTvShowsUpcomingHighlightUseCase::invoke.name} return Result Failure"
//        ) {
//            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
//            beforeEachGroup {
//                coEvery {
//                    getTvShowsUpcomingHighlightUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::tvShowsUpcomingData.name} should has Data Status Loading and Error sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.tvShowsUpcomingData.observeForever(observer)
//                runBlockingTest {
//                    viewModel.getTvShowsUpcomingHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(
//                            Data(
//                                Status.ERROR,
//                                errorMessage = "Something went wrong"
//                            )
//                        )
//                    }
//
//                    coVerify {
//                        getTvShowsUpcomingHighlightUseCase.invoke()
//                    }
//                }
//            }
//        }
//        context(
//            "when ${GetTvShowsUpcomingHighlightUseCase::class.java.simpleName}.${GetTvShowsUpcomingHighlightUseCase::invoke.name} return Result Success"
//        ) {
//            val resultDummy = Result.Success<List<Content>>(data = emptyList())
//            beforeEachGroup {
//                coEvery {
//                    getTvShowsUpcomingHighlightUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::tvShowsUpcomingData.name} should has Data Status Loading and Success sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.tvShowsUpcomingData.observeForever(observer)
//
//                runBlockingTest {
//                    viewModel.getTvShowsUpcomingHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
//                    }
//
//                    coVerify {
//                        getTvShowsUpcomingHighlightUseCase.invoke()
//                    }
//                }
//            }
//        }
//    }
//    describe(
//        "#${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::getMoviesPopularHighlight.name}"
//    ) {
//        context(
//            "when ${GetMoviesPopularHighlightUseCase::class.java.simpleName}.${GetMoviesPopularHighlightUseCase::invoke.name} return Result Failure"
//        ) {
//            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
//            beforeEachGroup {
//                coEvery {
//                    getMoviesPopularHighlightUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::moviesPopularData.name} should has Data Status Loading and Error sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.moviesPopularData.observeForever(observer)
//
//                runBlockingTest {
//                    viewModel.getMoviesPopularHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(
//                            Data(
//                                Status.ERROR,
//                                errorMessage = "Something went wrong"
//                            )
//                        )
//                    }
//
//                    coVerify {
//                        getMoviesPopularHighlightUseCase.invoke()
//                    }
//                }
//            }
//        }
//        context(
//            "when ${GetMoviesPopularHighlightUseCase::class.java.simpleName}.${GetMoviesPopularHighlightUseCase::invoke.name} return Result Success"
//        ) {
//            val resultDummy = Result.Success<List<Content>>(data = emptyList())
//            beforeEachGroup {
//                coEvery {
//                    getMoviesPopularHighlightUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::moviesPopularData.name} should has Data Status Loading and Success sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.moviesPopularData.observeForever(observer)
//
//                runBlockingTest {
//                    viewModel.getMoviesPopularHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
//                    }
//
//                    coVerify {
//                        getMoviesPopularHighlightUseCase.invoke()
//                    }
//                }
//            }
//        }
//    }
//    describe(
//        "#${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::getTvShowsPopularHighlight.name}"
//    ) {
//        context(
//            "when ${GetTvShowsPopularHighlightUseCase::class.java.simpleName}.${GetTvShowsPopularHighlightUseCase::invoke.name} return Result Failure"
//        ) {
//            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
//            beforeEachGroup {
//                coEvery {
//                    getTvShowsPopularHighlightUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::tvShowsPopularData.name} should has Data Status Loading and Error sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.tvShowsPopularData.observeForever(observer)
//
//                runBlockingTest {
//                    viewModel.getTvShowsPopularHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(
//                            Data(
//                                Status.ERROR,
//                                errorMessage = "Something went wrong"
//                            )
//                        )
//                    }
//
//                    coVerify {
//                        getTvShowsPopularHighlightUseCase.invoke()
//                    }
//                }
//            }
//        }
//        context(
//            "when ${GetTvShowsPopularHighlightUseCase::class.java.simpleName}.${GetTvShowsPopularHighlightUseCase::invoke.name} return Result Success"
//        ) {
//            val resultDummy = Result.Success<List<Content>>(data = emptyList())
//            beforeEachGroup {
//                coEvery {
//                    getTvShowsPopularHighlightUseCase.invoke()
//                } returns resultDummy
//            }
//            it(
//                "${HomeViewModelImpl::class.java.simpleName}.${HomeViewModelImpl::tvShowsPopularData.name} should has Data Status Loading and Success sequentially"
//            ) {
//                val observer: Observer<Data<List<Content>>> = mockk {
//                    every { onChanged(any()) } just Runs
//                }
//                viewModel.tvShowsPopularData.observeForever(observer)
//
//                runBlockingTest {
//                    viewModel.getTvShowsPopularHighlight()
//
//                    verifySequence {
//                        observer.onChanged(Data(Status.LOADING))
//                        observer.onChanged(Data(Status.SUCCESS, data = emptyList()))
//                    }
//
//                    coVerify {
//                        getTvShowsPopularHighlightUseCase.invoke()
//                    }
//                }
//            }
//        }
//    }
})