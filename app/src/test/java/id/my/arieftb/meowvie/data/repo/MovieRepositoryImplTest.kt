package id.my.arieftb.meowvie.data.repo

import com.google.common.truth.Truth.assertThat
import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import id.my.arieftb.meowvie.data.model.response.movies.detail.MovieDetailResponse
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
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

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetchAll.name} return response that 200 but null result") {
            val dummyResponse =
                TestHelper.createDummyResponse("movie/get-movies-result-null-response.json", MoviesResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequest)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetchAll.name} should return result success with empty data") {
                runBlocking {
                    val result = repository.fetchAll(dummyRequest, Movie())
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isEmpty()
                }
                coVerify {
                    remote.fetchAll(dummyRequest)
                }
            }
        }

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetchAll.name} return response that 200 but empty result") {
            val dummyResponse =
                TestHelper.createDummyResponse("movie/get-movies-result-empty-response.json", MoviesResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequest)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetchAll.name} should return result success with empty data") {
                runBlocking {
                    val result = repository.fetchAll(dummyRequest, Movie())
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isEmpty()
                }
                coVerify {
                    remote.fetchAll(dummyRequest)
                }
            }
        }

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetchAll.name} return response that 200 with result") {
            val dummyResponse =
                TestHelper.createDummyResponse("movie/get-movies-result-success-response.json", MoviesResponse::class.java)
            beforeEachGroup {
                coEvery {
                    remote.fetchAll(dummyRequest)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetchAll.name} should return result success with data") {
                runBlocking {
                    val result = repository.fetchAll(dummyRequest, Movie())
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isNotEmpty()
                }
                coVerify {
                    remote.fetchAll(dummyRequest)
                }
            }
        }
    }

    describe("#${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetch.name}") {
        val dummyRequestParam : DetailRequest = mockk()
        val dummyDataParam : MovieDetail = mockk(relaxed = true)

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetch.name} return response that not 200") {
            val dummyResponse = TestHelper.createDummyResponse(null, 500, MovieDetailResponse::class.java)

            beforeEachGroup {
                coEvery {
                    remote.fetch(dummyRequestParam)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetch.name} should return result failure") {
                runBlocking {
                    val result = repository.fetch(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetch(dummyRequestParam)
                }
            }
        }

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetch.name} return response that 200 but null body") {
            val dummyResponse = TestHelper.createDummyResponse(null, MovieDetailResponse::class.java)

            beforeEachGroup {
                coEvery {
                    remote.fetch(dummyRequestParam)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetch.name} should return result failure") {
                runBlocking {
                    val result = repository.fetch(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Failure).isTrue()
                    assertThat((result as Result.Failure).exception.message).isEqualTo("Something went wrong")
                }
                coVerify {
                    remote.fetch(dummyRequestParam)
                }
            }
        }

        context("when ${MovieRemoteDataSource::class.java.simpleName}.${MovieRemoteDataSource::fetch.name} return response that 200 with data") {
            val dummyResponse = TestHelper.createDummyResponse("movie/get-movie-success-response.json", MovieDetailResponse::class.java)

            beforeEachGroup {
                coEvery {
                    remote.fetch(dummyRequestParam)
                } returns dummyResponse
            }

            it("${MovieRepositoryImpl::class.java.simpleName}.${MovieRepositoryImpl::fetch.name} should return result success") {
                runBlocking {
                    val result = repository.fetch(dummyRequestParam, dummyDataParam)
                    assertThat(result is Result.Success).isTrue()
                    assertThat((result as Result.Success).data).isNotNull()
                }
                coVerify {
                    remote.fetch(dummyRequestParam)
                }
            }
        }
    }
})