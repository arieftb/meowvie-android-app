package id.my.arieftb.meowvie.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.meowvie.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.helper.TestHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TvShowRepositoryImplTest : Spek({
    @MockK
    lateinit var remote: TvShowRemoteDataSource
    val repository by memoized { TvShowRepositoryImpl(remote) }

    describe(
        "#${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name}"
    ) {
        val dummyRequestParam: DiscoverRequest = mockk()
        val dummyDataParam: TvShow = mockk(relaxed = true)
        remote = mockk(relaxed = true)
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetchAll.name} return response with code not 200"
        ) {
            val dummyResponse =
                TestHelper.createDummyResponse(null, 500, TvShowsResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns dummyResponse
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Failure"
            ) {
                runBlocking {
                    val result = repository.fetchAll(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetchAll(dummyRequestParam)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetchAll.name} return response with code 200 but null body"
        ) {
            val dummyResponse =
                TestHelper.createDummyResponse(null, TvShowsResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns dummyResponse
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Failure"
            ) {
                runBlocking {
                    val result = repository.fetchAll(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetchAll(dummyRequestParam)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetchAll.name} return response with code 200 but null result item"
        ) {
            val dummyResponse =
                TestHelper.createDummyResponse("tv/get-tv-shows-result-null-response.json", TvShowsResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns dummyResponse
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Failure"
            ) {
                runBlocking {
                    val result = repository.fetchAll(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetchAll(dummyRequestParam)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetchAll.name} return response with code 200 but empty result item"
        ) {
            val dummyResponse =
                TestHelper.createDummyResponse("tv/get-tv-shows-result-empty-response.json", TvShowsResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns dummyResponse
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Success with empty data"
            ) {
                runBlocking {
                    val result = repository.fetchAll(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isEmpty()
                }
                coVerify {
                    remote.fetchAll(dummyRequestParam)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetchAll.name} return response with code 200 with result items"
        ) {
            val dummyResponse =
                TestHelper.createDummyResponse("tv/get-tv-shows-result-success-response.json", TvShowsResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns dummyResponse
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Success with data"
            ) {
                runBlocking {
                    val result = repository.fetchAll(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isNotEmpty()
                }
                coVerify {
                    remote.fetchAll(dummyRequestParam)
                }
            }
        }
    }
})