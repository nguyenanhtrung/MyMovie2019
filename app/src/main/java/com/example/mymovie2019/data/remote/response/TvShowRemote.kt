package com.example.mymovie2019.data.remote.response


import com.squareup.moshi.Json

data class TvShowRemote(
    @Json(name = "backdrop_path")
    val backdropPath: String? = "", // /vBCZI4LTOVMGIlrBPhD1LQjDYtY.jpg
    @Json(name = "first_air_date")
    val firstAirDate: String? = "", // 1998-08-23
    @Json(name = "genre_ids")
    val genreIds: List<Int?>? = listOf(),
    @Json(name = "id")
    val id: Int? = 0, // 52
    @Json(name = "name")
    val name: String? = "", // That '70s Show
    @Json(name = "origin_country")
    val originCountry: List<String?>? = listOf(),
    @Json(name = "original_language")
    val originalLanguage: String? = "", // en
    @Json(name = "original_name")
    val originalName: String? = "", // That '70s Show
    @Json(name = "overview")
    val overview: String? = "", // That '70s Show is an American television period sitcom that originally aired on Fox from August 23, 1998, to May 18, 2006. The series focused on the lives of a group of teenage friends living in the fictional suburban town of Point Place, Wisconsin, from May 17, 1976, to December 31, 1979.The main teenage cast members were Topher Grace, Mila Kunis, Ashton Kutcher, Danny Masterson, Laura Prepon, and Wilmer Valderrama. The main adult cast members were Debra Jo Rupp, Kurtwood Smith, Don Stark and, during the first three seasons, Tanya Roberts.
    @Json(name = "popularity")
    val popularity: Double? = 0.0, // 15.40679
    @Json(name = "poster_path")
    val posterPath: String? = "", // /2eALZgo89aHezKDRjZMveRjD5gc.jpg
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0, // 7.13
    @Json(name = "vote_count")
    val voteCount: Int? = 0 // 61
)