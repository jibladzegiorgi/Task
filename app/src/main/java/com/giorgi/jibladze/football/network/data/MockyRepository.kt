package com.giorgi.jibladze.football.network.data

import androidx.lifecycle.LiveData
import com.giorgi.jibladze.football.model.MockyResult
import javax.inject.Inject

interface MockyRepositorySource {
    fun getMocky(): LiveData<Result<MockyResult>>
}

class MockyRepository @Inject constructor(
    private val remoteMockyDataSource: RemoteMockyDataSource
) : MockyRepositorySource {

    override fun getMocky(): LiveData<Result<MockyResult>> {
       return remoteMockyDataSource.getMocky()
    }
}
