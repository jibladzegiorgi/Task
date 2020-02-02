package com.giorgi.jibladze.football.network

import androidx.lifecycle.MediatorLiveData
import com.giorgi.jibladze.football.network.data.Result

abstract class MediatorUseCase<P, R> {

    protected val result = MediatorLiveData<Result<R>>()

    open fun observe(): MediatorLiveData<Result<R>> {
        return result
    }

    abstract fun execute(parameters: P)
}