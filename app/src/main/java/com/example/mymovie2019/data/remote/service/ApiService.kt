package com.example.mymovie2019.data.remote.service

import com.example.mymovie2019.data.remote.response.*
import com.example.mymovie2019.utils.AppKey
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    fun getGenresOfMovieAsync(@Query(AppKey.API_KEY_PARAMETER) apiKey: String): Deferred<GenreResponse>

    @GET("genre/tv/list")
    fun getGenresOfTvShowAsync(@Query(AppKey.API_KEY_PARAMETER) apiKey: String): Deferred<GenreResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String

    ): Deferred<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String

    ): Deferred<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String

    ): Deferred<MoviesResponse>

    @GET("person/popular")
    fun getPopularCasts(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<CastsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(
        @Path("movie_id") movieId: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getCreditMovieAsync(
        @Path("movie_id") movieId: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<MovieCreditResponse>

    @GET("person/{person_id}/tv_credits")
    fun getTvShowsOfCastAsync(
        @Path("person_id") castId: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<CastTvShowResponse>

    @GET("person/{person_id}/movie_credits")
    fun getMoviesOfCastAsync(
        @Path("person_id") castId: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<CastMovieResponse>

    @GET("tv/popular")
    fun getPopularTvShowsAsync(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<TvShowsResponse>

    @GET("tv/latest")
    fun getLatestTvShowsAsync(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<TvShowsResponse>

    @GET("tv/on_the_air")
    fun getOnTheAirTvShowsAsync(
        @Query(AppKey.PAGE_PARAMETER) page: Int,
        @Query(AppKey.API_KEY_PARAMETER) apiKey: String
    ): Deferred<TvShowsResponse>
}