package id.my.arieftb.meowvie.data.repo

import com.google.common.truth.Truth.assertThat
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
    }
})