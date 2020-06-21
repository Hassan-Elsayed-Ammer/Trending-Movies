package com.androidstation.trendingmovies.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.androidstation.trendingmovies.data.api.FIRST_PAGE
import com.androidstation.trendingmovies.data.api.TheMovieDBInterface
import com.androidstation.trendingmovies.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/*use this class to load data based page number
Int is Key and Movie is List of Movies
 */
class MovieDataSource(private val apiService: TheMovieDBInterface ,
                      private val compositeDisposable: CompositeDisposable)
                      :PageKeyedDataSource<Int ,Movie>(){

    private var page = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()



     override fun loadInitial(params: LoadInitialParams<Int>,callback: LoadInitialCallback<Int, Movie>) {
         networkState.postValue(NetworkState.LOADING)

         compositeDisposable.add(
             apiService.getPopularMovie(page)
                 .subscribeOn(Schedulers.io())
                 .subscribe(
                     {
                         callback.onResult(it.movieList,null ,page+1)
                         networkState.postValue(NetworkState.LOADED)
                     },
                     {
                        networkState.postValue(NetworkState.ERROR)
                         Log.e("MovieDataSource",it.message)
                     }
                 )
         )

     }

     override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
         networkState.postValue(NetworkState.LOADING)

         compositeDisposable.add(
             apiService.getPopularMovie(params.key)
                 .subscribeOn(Schedulers.io())
                 .subscribe(
                     {
                       if (it.totalPages>= params.key) {
                           callback.onResult(it.movieList, params.key+1 )
                           networkState.postValue(NetworkState.LOADED)
                       }else{
                           networkState.postValue(NetworkState.ENDOFLIST)
                       }
                     },
                     {
                         networkState.postValue(NetworkState.ERROR)
                         Log.e("MovieDataSource",it.message)
                     }
                 )
         )

     }

     override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

     }

 }