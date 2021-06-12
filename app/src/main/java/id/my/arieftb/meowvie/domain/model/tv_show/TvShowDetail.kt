package id.my.arieftb.meowvie.domain.model.tv_show

import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.data.model.response.tv_shows.detail.TvShowDetailResponse
import id.my.arieftb.meowvie.domain.base.BaseMapper

class TvShowDetail(
    var id: Long? = -1,
    var title: String? = "",
    var posterPath: String? = null,
    var releaseDate: String? = "",
    var overview: String? = null,
    var genre: String? = null,
    var rating: Double? = 0.0
): TvShowDetailMapper {
    override fun mapFromResponse(response: TvShowDetailResponse?): TvShowDetail? {
        return TvShowDetail().apply {
            id = response?.id
            title = response?.name
            posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response?.posterPath
            releaseDate = response?.firstAirDate
            overview = response?.overview
            genre = response?.genres?.joinToString(separator = ", ") {
                it.name.toString()
            }
            rating = response?.voteAverage
        }
    }
}

interface TvShowDetailMapper: BaseMapper.ResponseDataMapper<TvShowDetailResponse, TvShowDetail>