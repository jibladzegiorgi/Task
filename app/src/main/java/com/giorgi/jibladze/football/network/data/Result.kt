package com.giorgi.jibladze.football.network.data

sealed class Result<out R> {

    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val exception: String?) : Result<Nothing>()
}