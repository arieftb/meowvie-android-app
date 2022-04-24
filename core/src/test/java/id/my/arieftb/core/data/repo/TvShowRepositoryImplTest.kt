package id.my.arieftb.core.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.core.data.model.response.tv_shows.detail.TvShowDetailResponse
import id.my.arieftb.core.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.helper.TestHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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
        remote = mockk(relaxed = true)
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetchAll.name} return response with code not 200"
        ) {
            val dummyResponse =
                TestHelper.createDummyResponse(null, 500, TvShowsResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns flow {
                    emit(dummyResponse)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Failure"
            ) {
                runBlocking {
                    repository.fetchAll(dummyRequestParam).collect { result ->
                        assertThat(result is ResultEntity.Failure).isTrue()
                        assertThat((result as ResultEntity.Failure).exception.message).isEqualTo("Something went wrong")
                    }
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
                } returns flow {
                    emit(dummyResponse)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result empty"
            ) {
                runBlocking {
                    repository.fetchAll(dummyRequestParam).collect { result ->
                        assertThat(result is ResultEntity.Success).isTrue()
                        assertThat((result as ResultEntity.Success).data.isEmpty()).isTrue()
                    }
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
                TestHelper.createDummyResponse(
                    "tv/get-tv-shows-result-null-response.json",
                    TvShowsResponse::class.java
                )
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns flow {
                    emit(dummyResponse)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Empty"
            ) {
                runBlocking {
                    repository.fetchAll(dummyRequestParam).collect { result ->
                        assertThat(result is ResultEntity.Success).isTrue()
                        assertThat((result as ResultEntity.Success).data.isEmpty()).isTrue()
                    }
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
                TestHelper.createDummyResponse(
                    "tv/get-tv-shows-result-empty-response.json",
                    TvShowsResponse::class.java
                )
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns flow {
                    emit(dummyResponse)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Success with empty data"
            ) {
                runBlocking {
                    repository.fetchAll(dummyRequestParam).collect { result ->
                        assertThat(result is ResultEntity.Success).isTrue()
                        assertThat((result as ResultEntity.Success).data).isEmpty()
                    }
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
                TestHelper.createDummyResponse(
                    "tv/get-tv-shows-result-success-response.json",
                    TvShowsResponse::class.java
                )
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequestParam)
                } returns flow {
                    emit(dummyResponse)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetchAll.name} should return Result Success with data"
            ) {
                runBlocking {
                    repository.fetchAll(dummyRequestParam).collect { result ->
                        assertThat(result is ResultEntity.Success).isTrue()
                        assertThat((result as ResultEntity.Success).data).isNotEmpty()
                    }
                }
                coVerify {
                    remote.fetchAll(dummyRequestParam)
                }
            }
        }
    }
    describe(
        "#${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetch.name}"
    ) {
        val detailRequestParamDummy: DetailRequest = mockk()
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetch.name} return response with code not 200"
        ) {
            val responseDummy =
                TestHelper.createDummyResponse(null, 500, TvShowDetailResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetch(detailRequestParamDummy)
                } returns flow {
                    emit(responseDummy)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetch.name} should return Result Failure"
            ) {
                runBlocking {
                    repository.fetch(detailRequestParamDummy).collect { result ->
                        assertThat(result is ResultEntity.Failure).isTrue()
                        assertThat((result as ResultEntity.Failure).exception.message).isEqualTo("Something went wrong")
                    }
                }
                coVerify {
                    remote.fetch(detailRequestParamDummy)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetch.name} return response with code 200 with null body"
        ) {
            val responseDummy =
                TestHelper.createDummyResponse(null, TvShowDetailResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetch(detailRequestParamDummy)
                } returns flow {
                    emit(responseDummy)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetch.name} should return Result Failure"
            ) {
                runBlocking {
                    repository.fetch(detailRequestParamDummy).collect { result ->
                        assertThat(result is ResultEntity.Failure).isTrue()
                        assertThat((result as ResultEntity.Failure).exception.message).isEqualTo("Something went wrong")
                    }
                }
                coVerify {
                    remote.fetch(detailRequestParamDummy)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetch.name} return response with code 200 with not success"
        ) {
            val responseDummy =
                TestHelper.createDummyResponse(
                    "tv/get-tv-show-not-success-response.json",
                    TvShowDetailResponse::class.java
                )
            beforeEachGroup {
                coEvery {
                    remote.fetch(detailRequestParamDummy)
                } returns flow {
                    emit(responseDummy)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetch.name} should return Result Failure"
            ) {
                runBlocking {
                    repository.fetch(detailRequestParamDummy).collect { result ->
                        assertThat(result is ResultEntity.Failure).isTrue()
                        assertThat((result as ResultEntity.Failure).exception.message).isEqualTo("Something went wrong")
                    }
                }
                coVerify {
                    remote.fetch(detailRequestParamDummy)
                }
            }
        }
        context(
            "${TvShowRemoteDataSource::class.java.simpleName}.${TvShowRemoteDataSource::fetch.name} return response with code 200 with result"
        ) {
            val responseDummy = TestHelper.createDummyResponse(
                "tv/get-tv-show-success-response.json",
                TvShowDetailResponse::class.java
            )
            beforeEachGroup {
                coEvery {
                    remote.fetch(detailRequestParamDummy)
                } returns flow {
                    emit(responseDummy)
                }
            }

            it(
                "${TvShowRepositoryImpl::class.java.simpleName}.${TvShowRepositoryImpl::fetch.name} should return Result Success"
            ) {
                runBlocking {
                    repository.fetch(detailRequestParamDummy).collect { result ->
                        assertThat(result is ResultEntity.Success).isTrue()
                        assertThat((result as ResultEntity.Success).data).isNotNull()
                    }
                }
                coVerify {
                    remote.fetch(detailRequestParamDummy)
                }
            }
        }
    }
})