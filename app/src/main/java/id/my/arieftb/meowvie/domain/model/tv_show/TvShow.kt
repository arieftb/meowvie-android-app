package id.my.arieftb.meowvie.domain.model.tv_show

import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowResult
import id.my.arieftb.meowvie.domain.base.BaseMapper

class TvShow(
    var id: Long? = -1,
    var title: String? = "",
    var bannerPath: String? = null,
    var posterPath: String? = null,
    var releaseDate: String? = "",
    var episode: String? = ""
) : TvShowMapper {
    override fun mapFromResponse(response: TvShowResult?): TvShow {
        return TvShow().apply {
            id = response?.id?.toLong()
            title = response?.name
            bannerPath = response?.backdropPath
            posterPath = response?.posterPath
            releaseDate = response?.firstAirDate
        }
    }
}

interface TvShowMapper : BaseMapper.ResponseDataMapper<TvShowResult, TvShow>