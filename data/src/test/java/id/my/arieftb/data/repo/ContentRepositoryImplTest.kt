package id.my.arieftb.data.repo

import id.my.arieftb.data.remote.content.ContentRemoteDataSource
import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.request.content.ContentSearchRequest
import kotlinx.coroutines.runBlocking

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
                } returns dummyResponse
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} should return result failure with exception") {
                runBlocking {
                    val result = repository.search(dummyRequest, Content())
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
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
                 } returns dummyResponse
             }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} should return result failure with exception") {
                runBlocking {
                    val result = repository.search(dummyRequest, Content())
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 but result items null") {
            val dummyResponse =
                TestHelper.createDummyResponse("content/search-content-result-null-response.json", ContentSearchResponse::class.java)
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns dummyResponse
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} return result success with empty data") {
                runBlocking {
                    val result = repository.search(dummyRequest, Content())
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isEmpty()
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 but result items empty") {
            val dummyResponse =
                TestHelper.createDummyResponse("content/search-content-result-empty-response.json", ContentSearchResponse::class.java)
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns dummyResponse
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} return result success with empty data") {
                runBlocking {
                    val result = repository.search(dummyRequest, Content())
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isEmpty()
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }

        context("when ${ContentRemoteDataSource::class.java.simpleName}.${ContentRemoteDataSource::search.name} return response that 200 with results") {
            val dummyResponse =
                TestHelper.createDummyResponse("content/search-content-result-success-response.json", ContentSearchResponse::class.java)
            remote = mockk(relaxed = true)

            val dummyRequest = ContentSearchRequest("", 1, "")

            beforeEachGroup {
                coEvery {
                    remote.search(dummyRequest)
                } returns dummyResponse
            }


            it("${ContentRepositoryImpl::class.java.simpleName}.${ContentRepositoryImpl::search.name} return result success with data") {
                runBlocking {
                    val result = repository.search(dummyRequest, Content())
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isNotEmpty()
                }
                coVerify {
                    remote.search(dummyRequest)
                }
            }
        }
    }
})