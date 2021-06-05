package id.my.arieftb.meowvie.data.remote.movie

import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(val movieApiService: MovieApiService): MovieRemoteDataSource {

}