package id.my.arieftb.meowvie.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.data.model.response.contents.search.ContentSearchResponse
import id.my.arieftb.meowvie.data.remote.content.ContentRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.helper.TestHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object ContentRepositoryImplTest : Spek({

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
                TestHelper.createDummyResponse("content/get-content-result-null-response.json", ContentSearchResponse::class.java)
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
    }
})