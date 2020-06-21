package com.androidstation.trendingmovies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidstation.trendingmovies.data.api.TheMovieDBInterface
import com.androidstation.trendingmovies.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



//for call api by using RXjava it we return movie details
class MovieDetailsNetworkDataSource( private val apiService: TheMovieDBInterface,
    // compositeDisposable rx java components use it tp dispose (تخلص من ) api calls
                                     private val compositeDisposable: CompositeDisposable ) {

    //mutable live data
    private val _networkState  = MutableLiveData<NetworkState>()

    ////public networkState from  LiveData type
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedMovieDetailsResponse =  MutableLiveData<MovieDetails>()
    val downloadedMovieResponse: LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailsDataSource", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("MovieDetailsDataSource",e.message)
        }


    }
}
