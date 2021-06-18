package id.my.arieftb.meowvie.presentation.feature.detail

import androidx.lifecycle.Observer
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.usecase.movies.detail.GetMovieDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.detail.GetTvShowDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.watch_list.CheckWatchListUseCase
import id.my.arieftb.meowvie.domain.usecase.watch_list.RemoveWatchListUseCase
import id.my.arieftb.meowvie.domain.usecase.watch_list.SaveWatchListUseCase
import id.my.arieftb.meowvie.helper.applyInstantTaskExecutor
import id.my.arieftb.meowvie.helper.applyTestDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class DetailViewModelImplTest : Spek({
    applyTestDispatcher()
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
            val resultDummy = Result.Failure<ContentDetail>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getMovieDetailUseCase.invoke(idParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getMovieDetail.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<ContentDetail>> = mockk {
                    every { onChanged(any()) } just Runs
                }

                viewModel.detailData.observeForever(observer)
                viewModel.getMovieDetail(idParamDummy)

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    getMovieDetailUseCase.invoke(idParamDummy)
                }
            }
        }
        context(
            "when ${GetMovieDetailUseCase::class.java.simpleName}.${GetMovieDetailUseCase::invoke.name} return Result Success"
        ) {
            val contentDetailDummy = ContentDetail()
            val resultDummy = Result.Success(data = contentDetailDummy)
            beforeEachGroup {
                coEvery {
                    getMovieDetailUseCase.invoke(idParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getMovieDetail.name} should has Data Status Loading and Success sequentially"
            ) {
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
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getTvShowDetail.name}"
    ) {
        val idParamDummy = 1L
        context(
            "when ${GetTvShowDetailUseCase::class.java.simpleName}.${GetTvShowDetailUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<ContentDetail>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    getTvShowDetailUseCase.invoke(idParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getTvShowDetail.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<ContentDetail>> = mockk {
                    every { onChanged(any()) } just Runs
                }

                viewModel.detailData.observeForever(observer)
                viewModel.getTvShowDetail(idParamDummy)

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    getTvShowDetailUseCase.invoke(idParamDummy)
                }
            }
        }
        context(
            "when ${GetTvShowDetailUseCase::class.java.simpleName}.${GetTvShowDetailUseCase::invoke.name} return Result Success"
        ) {
            val contentDetailDummy = ContentDetail()
            val resultDummy = Result.Success(data = contentDetailDummy)
            beforeEachGroup {
                coEvery {
                    getTvShowDetailUseCase.invoke(idParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::getTvShowDetail.name} should has Data Status Loading and Success sequentially"
            ) {
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
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name}"
    ) {
        val idParamDummy = 1L
        val typeParamDummy = ContentType.MOVIE
        context(
            "when ${CheckWatchListUseCase::class.java.simpleName}.${CheckWatchListUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<Boolean>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name} should has Data Status Loading and Failure sequentially"
            ) {
                val observer: Observer<Data<Boolean>> = mockk {
                    every { onChanged(any()) } just Runs
                }

                viewModel.isAvailable.observeForever(observer)
                viewModel.checkWatchList(idParamDummy, typeParamDummy)

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                }
            }
        }
        context(
            "when ${CheckWatchListUseCase::class.java.simpleName}.${CheckWatchListUseCase::invoke.name} return Result Success true"
        ) {
            val resultDummy = Result.Success(true)
            beforeEachGroup {
                coEvery {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name} should has Data Status Loading and Success True sequentially"
            ) {
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
        context(
            "when ${CheckWatchListUseCase::class.java.simpleName}.${CheckWatchListUseCase::invoke.name} return Result Success false"
        ) {
            val resultDummy = Result.Success(false)
            beforeEachGroup {
                coEvery {
                    checkWatchListUseCase.invoke(idParamDummy, typeParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::checkWatchList.name} should has Data Status Loading and Success false sequentially"
            ) {
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
    describe(
        "#${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::saveWatchList.name}"
    ) {
        val contentParamDummy = Content()
        context(
            "when ${SaveWatchListUseCase::class.java.simpleName}.${SaveWatchListUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<Boolean>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    saveWatchListUseCase.invoke(contentParamDummy)
                } returns resultDummy
            }
            it(
                "${DetailViewModelImpl::class.java.simpleName}.${DetailViewModelImpl::saveWatchList.name} should has Data Status Loading and Failure sequentially"
            ) {
                val observer: Observer<Data<Boolean>> = mockk {
                    every { onChanged(any()) } just Runs
                }

                viewModel.isSaved.observeForever(observer)
                viewModel.saveWatchList(contentParamDummy)

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    saveWatchListUseCase.invoke(contentParamDummy)
                }
            }
        }
    }
})