package id.my.arieftb.core.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.core.data.model.request.content.ContentSearchRequest
import id.my.arieftb.core.data.model.response.contents.search.ContentSearchResponse
import id.my.arieftb.core.data.remote.content.ContentRemoteDataSource
import id.my.arieftb.core.domain.model.Result
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


class ContentRepositoryImplTest : Spek({

    @MockK
    lateinit var remote: ContentRemoteDataSource
    val repository by memoized { ContentRepositoryImpl(remote) }

    describe("#${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name}") {

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that not 200") {
            val dummyResponse =
                TestHelper.createDummyResponse(null, 500, ContentSearchResponse::class.java)
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns flow {
                    emit(dummyResponse)
                }
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} should return result failure with exception") {
                runBlocking {
                    repository.search(dummyRequest).collect { result ->
                        assertThat(result is Result.Failure).isTrue()
                        assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong.")
                    }
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 but body null") {
            val dummyResponse =
                TestHelper.createDummyResponse(null, ContentSearchResponse::class.java)
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns flow {
                    emit(dummyResponse)
                }
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} should return result failure with exception") {
                runBlocking {
                    repository.search(dummyRequest).collect { result ->
                        assertThat(result is Result.Success).isTrue()
                        assertThat((result as Result.Success).data.isEmpty()).isTrue()
                    }
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 but result items null") {
            val dummyResponse =
                TestHelper.createDummyResponse(
                    "content/search-content-result-null-response.json",
                    ContentSearchResponse::class.java
                )
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns flow {
                    emit(dummyResponse)
                }
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} return result success with empty data") {
                runBlocking {
                    repository.search(dummyRequest).collect { result ->
                        assertThat(result is Result.Success).isTrue()
                        assertThat((result as Result.Success).data).isEmpty()
                    }
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 but result items empty") {
            val dummyResponse =
                TestHelper.createDummyResponse(
                    "content/search-content-result-empty-response.json",
                    ContentSearchResponse::class.java
                )
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns flow {
                    emit(dummyResponse)
                }
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} return result success with empty data") {
                runBlocking {
                    repository.search(dummyRequest).collect { result ->
                        assertThat(result is Result.Success).isTrue()
                        assertThat((result as Result.Success).data).isEmpty()
                    }
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 with results") {
            val dummyResponse =
                TestHelper.createDummyResponse(
                    "content/search-content-result-success-response.json",
                    ContentSearchResponse::class.java
                )
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns flow {
                    emit(dummyResponse)
                }
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} return result success with data") {
                runBlocking {
                    repository.search(dummyRequest).collect { result ->
                        assertThat(result is Result.Success).isTrue()
                        assertThat((result as Result.Success).data).isNotEmpty()
                    }
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }
    }
})