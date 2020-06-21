package com.androidstation.trendingmovies.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.androidstation.trendingmovies.data.api.TheMovieDBInterface
import com.androidstation.trendingmovies.data.repository.MovieDetailsNetworkDataSource
import com.androidstation.trendingmovies.data.repository.NetworkState
import com.androidstation.trendingmovies.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepo (private val apiService: TheMovieDBInterface){

    lateinit var movieDetailsNetworkDataSource :MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable , movieId: Int):LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    // i can cache data here
    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return  movieDetailsNetworkDataSource.networkState
    }
}