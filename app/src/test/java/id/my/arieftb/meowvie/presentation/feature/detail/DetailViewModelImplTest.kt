package id.my.arieftb.meowvie.presentation.feature.detail

import androidx.lifecycle.Observer
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.usecase.movies.detail.GetMovieDetailUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.detail.GetTvShowDetailUseCase
import id.my.arieftb.core.domain.usecase.watch_list.CheckWatchListUseCase
import id.my.arieftb.core.domain.usecase.watch_list.RemoveWatchListUseCase
import id.my.arieftb.core.domain.usecase.watch_list.SaveWatchListUseCase
import id.my.arieftb.meowvie.helper.applyInstantTaskExecutor
import id.my.arieftb.meowvie.helper.applyTestDispatcher
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
class DetailViewModelImplTest : Spek({
    val testCoroutineDispatcher = TestCoroutineDispatcher()
    applyTestDispatcher(testCoroutineDispatcher)
    applyInstantTaskExecutor()

    val getMovieDetailUseCase: GetMovieDetailUseCase = mockk(relaxed = true)
    val getTvShowDetailUseCase: GetTvShowDetailUseCase = mockk(relaxed = true)
    val saveWatchListUseCase: SaveWatchListUseCase = mockk(relaxed = true)
    val checkWatchListUseCase: CheckWatchListUseCase = mockk(relaxed = true)
    val removeWatchListUseCase: RemoveWatchListUseCase = mockk(relaxed = true)
    val viewModel by memoized {
        DetailViewModelImpl(
            getMovieDetailUseCase,
            getTvShowDetailUseCase,
            saveWatchListUseCase,
            checkWatchListUseCase,
            removeWatchListUseCase
        )
    }


    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getMovieDetail.name}"
    ) {
        val idParamDummy = 1L
        context(
            "when ${GetMovieDetailUseCase::class.java.simpleName}.${GetMovieDetailUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<ContentDetail>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMovieDetailUseCase.invoke(idParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getMovieDetail.name} should has Data Status Loading and Error sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<ContentDetail>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.detailData.observeForever(observer)

                    viewModel.getMovieDetail(idParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getMovieDetailUseCase.invoke(idParamDummy)
                    }
                }
            }
        }
        context(
            "when ${GetMovieDetailUseCase::class.java.simpleName}.${GetMovieDetailUseCase::invoke.name} return Result Success"
        ) {
            val contentDetailDummy = ContentDetail(1L, "Dummy")
            val resultDummy = ResultEntity.Success(data = contentDetailDummy)
            beforeEachGroup {
                coEvery {
                    getMovieDetailUseCase.invoke(idParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getMovieDetail.name} should has Data Status Loading and Success sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<ContentDetail>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.detailData.observeForever(observer)

                    viewModel.getMovieDetail(idParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = contentDetailDummy))
                    }

                    coVerify {
                        getMovieDetailUseCase.invoke(idParamDummy)
                    }
                }
            }
        }
    }
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getTvShowDetail.name}"
    ) {
        val idParamDummy = 1L
        context(
            "when ${GetTvShowDetailUseCase::class.java.simpleName}.${GetTvShowDetailUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<ContentDetail>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowDetailUseCase.invoke(idParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getTvShowDetail.name} should has Data Status Loading and Error sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<ContentDetail>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.detailData.observeForever(observer)

                    viewModel.getTvShowDetail(idParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        getTvShowDetailUseCase.invoke(idParamDummy)
                    }
                }
            }
        }
        context(
            "when ${GetTvShowDetailUseCase::class.java.simpleName}.${GetTvShowDetailUseCase::invoke.name} return Result Success"
        ) {
            val contentDetailDummy = ContentDetail(1L, "Dummy")
            val resultDummy = ResultEntity.Success(data = contentDetailDummy)
            beforeEachGroup {
                coEvery {
                    getTvShowDetailUseCase.invoke(idParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getTvShowDetail.name} should has Data Status Loading and Success sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<ContentDetail>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.detailData.observeForever(observer)

                    viewModel.getTvShowDetail(idParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = contentDetailDummy))
                    }

                    coVerify {
                        getTvShowDetailUseCase.invoke(idParamDummy)
                    }
                }
            }
        }
    }
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name}"
    ) {
        val idParamDummy = 1L
        val typeParamDummy = ContentType.MOVIE
        context(
            "when ${CheckWatchListUseCase::class.java.simpleName}.${CheckWatchListUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<Boolean>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name} should has Data Status Loading and Failure sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isAvailable.observeForever(observer)

                    viewModel.checkWatchList(idParamDummy, typeParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                    }
                }
            }
        }
        context(
            "when ${CheckWatchListUseCase::class.java.simpleName}.${CheckWatchListUseCase::invoke.name} return Result Success true"
        ) {
            val resultDummy = ResultEntity.Success(true)
            beforeEachGroup {
                coEvery {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name} should has Data Status Loading and Success True sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isAvailable.observeForever(observer)

                    viewModel.checkWatchList(idParamDummy, typeParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = true))
                    }

                    coVerify {
                        checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                    }
                }
            }
        }
        context(
            "when ${CheckWatchListUseCase::class.java.simpleName}.${CheckWatchListUseCase::invoke.name} return Result Success false"
        ) {
            val resultDummy = ResultEntity.Success(false)
            beforeEachGroup {
                coEvery {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name} should has Data Status Loading and Success false sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isAvailable.observeForever(observer)

                    viewModel.checkWatchList(idParamDummy, typeParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = false))
                    }

                    coVerify {
                        checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                    }
                }
            }
        }
    }
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::saveWatchList.name}"
    ) {
        val contentParamDummy = Content(1L, "Dummy")
        context(
            "when ${SaveWatchListUseCase::class.java.simpleName}.${SaveWatchListUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<Boolean>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    saveWatchListUseCase.invoke(contentParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::saveWatchList.name} should has Data Status Loading and Failure sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isSaved.observeForever(observer)

                    viewModel.saveWatchList(contentParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        saveWatchListUseCase.invoke(contentParamDummy)
                    }
                }
            }
        }
        context(
            "when ${SaveWatchListUseCase::class.java.simpleName}.${SaveWatchListUseCase::invoke.name} return Result Success True"
        ) {
            val resultDummy = ResultEntity.Success(true)
            beforeEachGroup {
                coEvery {
                    saveWatchListUseCase.invoke(contentParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::saveWatchList.name} should has Data Status Loading and Success True sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isSaved.observeForever(observer)

                    viewModel.saveWatchList(contentParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = true))
                    }

                    coVerify {
                        saveWatchListUseCase.invoke(contentParamDummy)
                    }
                }
            }
        }
        context(
            "when ${SaveWatchListUseCase::class.java.simpleName}.${SaveWatchListUseCase::invoke.name} return Result Success False"
        ) {
            val resultDummy = ResultEntity.Success(false)
            beforeEachGroup {
                coEvery {
                    saveWatchListUseCase.invoke(contentParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::saveWatchList.name} should has Data Status Loading and Success False sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isSaved.observeForever(observer)

                    viewModel.saveWatchList(contentParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = false))
                    }

                    coVerify {
                        saveWatchListUseCase.invoke(contentParamDummy)
                    }
                }
            }
        }
    }
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::removeContent.name}"
    ) {
        val idParamDummy = 1L
        val typeParamDummy = ContentType.MOVIE
        context(
            "when ${RemoveWatchListUseCase::class.java.simpleName}.${RemoveWatchListUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = ResultEntity.Failure<Boolean>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    removeWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::removeContent.name} should has Data Status Loading and Failure sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isSaved.observeForever(observer)

                    viewModel.removeContent(idParamDummy, typeParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(
                            Data(
                                Status.ERROR,
                                errorMessage = "Something went wrong"
                            )
                        )
                    }

                    coVerify {
                        removeWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                    }
                }
            }
        }
        context(
            "when ${RemoveWatchListUseCase::class.java.simpleName}.${RemoveWatchListUseCase::invoke.name} return Result Success True"
        ) {
            val resultDummy = ResultEntity.Success(true)
            beforeEachGroup {
                coEvery {
                    removeWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::removeContent.name} should has Data Status Loading and Success True sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isSaved.observeForever(observer)

                    viewModel.removeContent(idParamDummy, typeParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = true))
                    }

                    coVerify {
                        removeWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                    }
                }
            }
        }
        context(
            "when ${RemoveWatchListUseCase::class.java.simpleName}.${RemoveWatchListUseCase::invoke.name} return Result Success False"
        ) {
            val resultDummy = ResultEntity.Success(false)
            beforeEachGroup {
                coEvery {
                    removeWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns flow {
                    emit(resultDummy)
                }
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::removeContent.name} should has Data Status Loading and Success False sequentially"
            ) {
                testCoroutineDispatcher.runBlockingTest {
                    val observer: Observer<Data<Boolean>> = mockk {
                        every { onChanged(any()) } just Runs
                    }

                    viewModel.isSaved.observeForever(observer)

                    viewModel.removeContent(idParamDummy, typeParamDummy)
                    verifySequence {
                        observer.onChanged(Data(Status.LOADING))
                        observer.onChanged(Data(Status.SUCCESS, data = false))
                    }

                    coVerify {
                        removeWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                    }
                }
            }
        }
    }
})