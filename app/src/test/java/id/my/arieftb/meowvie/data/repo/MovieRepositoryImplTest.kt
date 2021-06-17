package id.my.arieftb.meowvie.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.helper.TestHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class MovieRepositoryImplTest : Spek({
    @MockK
    lateinit var remote: MovieRemoteDataSource

    val repository by memoized { MovieRepositoryImpl(remote) }

    describe("#${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetchAll.name}") {

        val dummyRequest = DiscoverRequest()
        remote = mockk(relaxed = true)
        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetchAll.name} return response that not 200") {
            val dummyResponse =
                TestHelper.createDummyResponse(null, 500, MoviesResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequest)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetchAll.name} should return result failure") {
                runBlocking {
                    val result = repository.fetchAll(dummyRequest, Movie())
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetchAll(dummyRequest)
                }
            }
        }

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetchAll.name} return response that 200 but null body") {
            val dummyResponse =
                TestHelper.createDummyResponse(null, MoviesResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequest)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetchAll.name} should return result failure") {
                runBlocking {
                    val result = repository.fetchAll(dummyRequest, Movie())
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetchAll(dummyRequest)
                }
            }
        }
    }
})