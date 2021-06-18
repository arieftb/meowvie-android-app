package id.my.arieftb.meowvie.presentation.feature.explore

import androidx.lifecycle.Observer
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.contents.SearchContentsUseCase
import id.my.arieftb.meowvie.helper.applyInstantTaskExecutor
import id.my.arieftb.meowvie.helper.applyTestDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@OptIn(ExperimentalCoroutinesApi::class)
class ExploreViewModelImplTest : Spek({

    applyTestDispatcher()
    applyInstantTaskExecutor()

    val searchContentUseCase: SearchContentsUseCase = mockk(relaxed = true)
    val viewModel by memoized { ExploreViewModelImpl(searchContentUseCase) }

    describe(
        "#${ExploreViewModelImpl::class.java.simpleName}.${ExploreViewModelImpl::search.name}"
    ) {
        val pageParamDummy = 1
        val keywordParamDummy = "avenger"
        context(
            "when ${SearchContentsUseCase::class.java.simpleName}.${SearchContentsUseCase::invoke.name} return Result Failure"
        ) {
            val resultDummy = Result.Failure<List<Content>>(Exception("Something went wrong"))
            beforeEachGroup {
                coEvery {
                    searchContentUseCase.invoke(pageParamDummy, keywordParamDummy)
                } returns resultDummy
            }
            it(
                "${ExploreViewModelImpl::class.java.simpleName}.${ExploreViewModelImpl::searchData.name} should has Data Status Loading and Error sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }

                viewModel.searchData.observeForever(observer)
                viewModel.search(pageParamDummy, keywordParamDummy)

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.ERROR, errorMessage = "Something went wrong"))
                }

                coVerify {
                    searchContentUseCase.invoke(pageParamDummy, keywordParamDummy)
                }
            }
        }
        context(
            "when ${SearchContentsUseCase::class.java.simpleName}.${SearchContentsUseCase::invoke.name} return Result Success"
        ) {
            val resultDummy = Result.Success<List<Content>>(data = emptyList())
            beforeEachGroup {
                coEvery {
                    searchContentUseCase.invoke(pageParamDummy, keywordParamDummy)
                } returns resultDummy
            }
            it(
                "${ExploreViewModelImpl::class.java.simpleName}.${ExploreViewModelImpl::searchData.name} should has Data Status Loading and Success sequentially"
            ) {
                val observer: Observer<Data<List<Content>>> = mockk {
                    every { onChanged(any()) } just Runs
                }

                viewModel.searchData.observeForever(observer)
                viewModel.search(pageParamDummy, keywordParamDummy)

                verifySequence {
                    observer.onChanged(Data(Status.LOADING))
                    observer.onChanged(Data(Status.SUCCESS, emptyList()))
                }

                coVerify {
                    searchContentUseCase.invoke(pageParamDummy, keywordParamDummy)
                }
            }
        }
    }
})