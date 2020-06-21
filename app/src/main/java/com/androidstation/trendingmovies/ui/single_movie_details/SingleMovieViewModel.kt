package com.androidstation.trendingmovies.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidstation.trendingmovies.data.repository.NetworkState
import com.androidstation.trendingmovies.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel (private val movieRepository : MovieDetailsRepo , movieId: Int ) :ViewModel (){

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    // we call this function when Activity or fragment get destroy to avoid memory leak
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}