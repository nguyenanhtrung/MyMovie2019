package com.example.mymovie2019.data.repository.cast

import com.example.mymovie2019.data.local.datasource.cast.CastLocalDataSource
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract
import com.example.mymovie2019.data.remote.datasource.cast.CastRemoteDataSource
import com.example.mymovie2019.data.remote.response.*
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CastRepositoryImp @Inject constructor(private val castRemoteDataSource: CastRemoteDataSource,
                                            private val castLocalDataSource: CastLocalDataSource) : CastRepository {


    override fun getCastsOfMovieAsync(movieId: Int): Deferred<MovieCreditResponse> {
       return castRemoteDataSource.getCastsOfMovieAsync(movieId)
    }

    override fun saveCasts(casts: List<Cast>) {
        castLocalDataSource.saveCasts(casts)
    }

    override fun getCastLocalOfMovie(movieId: Int): List<Cast> {
        return castLocalDataSource.getCastLocalOfMovie(movieId)
    }

    override fun getPopularCastsAsync(page: Int): Deferred<CastsResponse> {
        return castRemoteDataSource.getPopularCastsAsync(page)
    }

    override fun getMoviesOfCastAsync(castId: Int): Deferred<CastMovieResponse> =
        castRemoteDataSource.getMoviesOfCastAsync(castId)

    override fun getTvShowsOfCastAsync(castId: Int): Deferred<CastTvShowResponse> =
        castRemoteDataSource.getTvShowsOfCastAsync(castId)

    override fun parseToCastTransfer(castTransferContract: CastTransferContract): CastTransfer {
        return castLocalDataSource.parseToCastTransfer(castTransferContract)
    }



}