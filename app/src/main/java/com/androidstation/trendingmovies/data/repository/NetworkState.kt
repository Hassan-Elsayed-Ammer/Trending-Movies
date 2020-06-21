package com.androidstation.trendingmovies.data.repository

//enum class has 3 value
enum class Status{
    RUNNING ,
    SUCCESS ,
    FAILED
}
//class to state of network
class NetworkState (val status: Status , val msg : String ){

    //to initialize static fields
    companion object {
        //we have 3 static fields

        val LOADED : NetworkState
        val LOADING : NetworkState
        val ERROR : NetworkState
        val ENDOFLIST : NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS , "Success")

            LOADING = NetworkState(Status.RUNNING , "Running")

            ERROR = NetworkState(Status.FAILED , "Failed")

            ENDOFLIST = NetworkState(Status.FAILED , "You Have Reached The End")
        }

    }
}