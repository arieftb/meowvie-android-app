package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.data.remote.content.ContentRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val remote: ContentRemoteDataSource
) : ContentRepository {
    override suspend fun search(
        request: ContentSearchRequest,
        data: Content
    ): Result<List<Content>> {
        val response = remote.search(request)
        if (response.isSuccessful) {
            if (response.body() != null) {
                return Result.Success(data = response.body()?.contentResults?.map {
                    data.mapFromSearchResult(it)
                }?.toList() ?: emptyList())
            }
            return Result.Failure(Exception("Something went wrong"))
        }
        return Result.Failure(Exception("Something went wrong"))
    }
}