package id.my.arieftb.meowvie.presentation.feature.section

import androidx.lifecycle.Observer
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.core.domain.usecase.movies.popular.GetMoviesPopularUseCase
import id.my.arieftb.core.domain.usecase.movies.upcoming.GetMoviesUpcomingUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.GetTvShowsUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.popular.GetTvShowsPopularUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingUseCase
import id.my.arieftb.core.helper.applyInstantTaskExecutor
import id.my.arieftb.core.helper.applyTestDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@OptIn(ExperimentalCoroutinesApi::class)
class SectionViewModelImplTest : Spek({
    val testDispatcher = TestCoroutineDispatcher()
    applyTestDispatcher(testDispatcher)
    applyInstantTaskExecutor()

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
            val resultDummy = ResultEntity.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMoviesUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

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
            val resultDummy = ResultEntity.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getMoviesUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

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
            val resultDummy = ResultEntity.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowsUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

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
            val resultDummy = ResultEntity.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getTvShowsUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

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

    describe(
        "#${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::getUpComingMovies.name}"
    ) {
        context(
            "when ${GetMoviesUpcomingUseCase::class.java.simpleName}.${GetMoviesUpcomingUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMoviesUpcomingUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getUpComingMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getMoviesUpcomingUseCase
                            .invoke()
                    }
                }
            }
        }
        context(
            "when ${GetMoviesUpcomingUseCase::class.java.simpleName}.${GetMoviesUpcomingUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = ResultEntity.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getMoviesUpcomingUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getUpComingMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.SUCCESS,
                                data = emptyList()
                            )
                        )
                    }

                    coVerify {
                        getMoviesUpcomingUseCase
                            .invoke()
                    }
                }
            }
        }
    }

    describe(
        "#${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::getUpComingTvShows.name}"
    ) {
        context(
            "when ${GetTvShowsUpcomingUseCase::class.java.simpleName}.${GetTvShowsUpcomingUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowsUpcomingUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getUpComingTvShows()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getTvShowsUpcomingUseCase
                            .invoke()
                    }
                }
            }
        }
        context(
            "when ${GetTvShowsUpcomingUseCase::class.java.simpleName}.${GetTvShowsUpcomingUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = ResultEntity.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getTvShowsUpcomingUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getUpComingMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.SUCCESS,
                                data = emptyList()
                            )
                        )
                    }

                    coVerify {
                        getTvShowsUpcomingUseCase
                            .invoke()
                    }
                }
            }
        }
    }
    describe(
        "#${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::getPopularMovies.name}"
    ) {
        context(
            "when ${GetMoviesPopularUseCase::class.java.simpleName}.${GetMoviesPopularUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMoviesPopularUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getPopularMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getMoviesPopularUseCase
                            .invoke()
                    }
                }
            }
        }
        context(
            "when ${GetMoviesPopularUseCase::class.java.simpleName}.${GetMoviesPopularUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = ResultEntity.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getMoviesPopularUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getPopularMovies()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.SUCCESS,
                                data = emptyList()
                            )
                        )
                    }

                    coVerify {
                        getMoviesPopularUseCase
                            .invoke()
                    }
                }
            }
        }
    }
    describe(
        "#${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::getPopularTvShows.name}"
    ) {
        context(
            "when ${GetTvShowsPopularUseCase::class.java.simpleName}.${GetTvShowsPopularUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowsPopularUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getPopularTvShows()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getTvShowsPopularUseCase
                            .invoke()
                    }
                }
            }
        }
        context(
            "when ${GetTvShowsPopularUseCase::class.java.simpleName}.${GetTvShowsPopularUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = ResultEntity.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    getTvShowsPopularUseCase.invoke()
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${SectionViewModelImpl::class.java.simpleName}.${SectionViewModelImpl::contentData.name} should has Data Status Loading and Error sequentially"
            ) {
                testDispatcher.runBlockingTest {
                    val observer: Observer<Data<List<Content>>> = mockk {
                        every { onChanged(any()) } just Runs
                    }
                    viewModel.contentData.observeForever(observer)

                    viewModel.getPopularTvShows()

                    verifySequence {
                        observer.onChanged(
                            Data(
                                Status.SUCCESS,
                                data = emptyList()
                            )
                        )
                    }

                    coVerify {
                        getTvShowsPopularUseCase
                            .invoke()
                    }
                }
            }
        }
    }
})