package com.androidstation.trendingmovies.data.api

import com.androidstation.trendingmovies.data.vo.MovieDetails
import com.androidstation.trendingmovies.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {
    /* get popular movie
    https://api.themoviedb.org/3/movie/popular?api_key=6f470fd5c844a81d1e0974b986a69acf

    get  one movie id
    https://api.themoviedb.org/3/movie/419704?api_key=6f470fd5c844a81d1e0974b986a69acf

    get base url
    https://api.themoviedb.org/3/
    */

    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page :Int):Single<MovieResponse>
    //put retrofit annotation GET
    @GET( "movie/{movie_id}" )
    //function to get movie details by id
    //@Path tall retrofit this (id:Int) is a {movie_id}
    //this function return single observable of data stream in (RXJava)  of Movie Details
    fun getMovieDetails (@Path ("movie_id" ) id :Int) :Single<MovieDetails>

}