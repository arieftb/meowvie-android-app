package id.my.arieftb.meowvie.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.local.watch_list.WatchListLocalDataSource
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class WatchListRepositoryImplTest : Spek({
    @MockK
    lateinit var local: WatchListLocalDataSource
    val repository by memoized { WatchListRepositoryImpl(local) }

    describe(
        "#${WatchListRepositoryImpl::class.java.simpleName}.${WatchListRepositoryImpl::saveWatchList.name}"
    ) {
        val contentSaveRequestDummyData: ContentSaveRequest = mockk()
        local = mockk(relaxed = true)
        context(
            "when ${WatchListLocalDataSource::class.java.simpleName}.${WatchListLocalDataSource::saveWatchList.name} return -1"
        ) {
            val responseDummy = -1L
            beforeEachGroup {
                coEvery {
                    local.saveWatchList(contentSaveRequestDummyData)
                } returns responseDummy
            }
            it(
                "${WatchListRepositoryImpl::class.java.simpleName}.${WatchListRepositoryImpl::saveWatchList.name} should return Result Failure"
            ) {
                runBlocking {
                    val result = repository.saveWatchList(contentSaveRequestDummyData)
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    local.saveWatchList(contentSaveRequestDummyData)
                }
            }
        }
        context(
            "when ${WatchListLocalDataSource::class.java.simpleName}.${WatchListLocalDataSource::saveWatchList.name} return not -1"
        ) {
            val responseDummy = 0L
            beforeEachGroup {
                coEvery {
                    local.saveWatchList(contentSaveRequestDummyData)
                } returns responseDummy
            }
            it(
                "${WatchListRepositoryImpl::class.java.simpleName}.${WatchListRepositoryImpl::saveWatchList.name} should return Result Success with true"
            ) {
                runBlocking {
                    val result = repository.saveWatchList(contentSaveRequestDummyData)
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isTrue()
                }
                coVerify {
                    local.saveWatchList(contentSaveRequestDummyData)
                }
            }
        }
    }
    describe(
        "#${WatchListRepositoryImpl::class.java.simpleName}.${WatchListRepositoryImpl::checkWatchList.name}"
    ) {
        val codeRequestParamDummy = 1L
        val typeRequestParamDummy = ContentType.MOVIE
        context(
            "when ${WatchListLocalDataSource::class.java.simpleName}.${WatchListLocalDataSource::checkWatchList.name} return null"
        ) {
            val responseDummy = null
            beforeEachGroup {
                coEvery {
                    local.checkWatchList(codeRequestParamDummy, typeRequestParamDummy)
                } returns responseDummy
            }
            it(
                "${WatchListRepositoryImpl::class.java.simpleName}.${WatchListRepositoryImpl::checkWatchList.name} should return Result Success with false"
            ) {
                runBlocking {
                    val result = repository.checkWatchList(codeRequestParamDummy, typeRequestParamDummy)
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isFalse()
                }
                coVerify {
                    local.checkWatchList(codeRequestParamDummy, typeRequestParamDummy)
                }
            }
        }
    }
})