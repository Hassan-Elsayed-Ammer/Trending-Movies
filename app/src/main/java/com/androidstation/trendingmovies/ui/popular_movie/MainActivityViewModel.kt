package com.androidstation.trendingmovies.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidstation.trendingmovies.data.repository.NetworkState
import com.androidstation.trendingmovies.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel (private val movieRepository:MoviePageListRepository):ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val moviePagedListed : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveDataPagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listsEmpty(): Boolean{
        return moviePagedListed.value?.isEmpty()?:true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}