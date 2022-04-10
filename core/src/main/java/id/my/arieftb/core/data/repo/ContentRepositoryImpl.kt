package id.my.arieftb.core.data.repo

import id.my.arieftb.core.data.model.request.content.ContentSearchRequest
import id.my.arieftb.core.data.remote.content.ContentRemoteDataSource
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.repo.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val remote: ContentRemoteDataSource
) : ContentRepository {
    override fun search(request: ContentSearchRequest): Flow<Result<List<Content>>> {
        return remote.search(request).map { response ->
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(response.body()?.contentResults?.map {
                        it.toContent()
                    }?.toList() ?: emptyList())
                } else {
                    Result.Success(emptyList())
                }
            } else {
                Result.Failure(Exception("Something went wrong."))
            }
        }
    }
}