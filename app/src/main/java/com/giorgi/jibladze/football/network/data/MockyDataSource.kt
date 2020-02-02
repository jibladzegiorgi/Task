package com.giorgi.jibladze.football.network.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giorgi.jibladze.football.model.MockyResult
import com.giorgi.jibladze.football.network.FootballService
import com.giorgi.jibladze.football.network.ImpRetrofitCallback
import javax.inject.Inject

interface RemoteMockyDataSource {
    fun getMocky(): LiveData<Result<MockyResult>>
}

class MockyDataSource @Inject constructor(
    private val api: FootballService
) : RemoteMockyDataSource {

    val result = MutableLiveData<Result<MockyResult>>()

    override fun getMocky(): LiveData<Result<MockyResult>> {
        api.getMockyResult().enqueue(object : ImpRetrofitCallback<MockyResult>() {
            override fun error(m: String?) {
                result.postValue(Result.Error(m))
            }

            override fun successLoadData(body: MockyResult?) {
                result.postValue(Result.Success(body))
            }
        })

        return result
    }

}