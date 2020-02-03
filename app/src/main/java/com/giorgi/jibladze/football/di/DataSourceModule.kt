package com.giorgi.jibladze.football.di

import com.giorgi.jibladze.football.network.FootballService
import com.giorgi.jibladze.football.network.data.datasource.MockyDataSource
import com.giorgi.jibladze.football.network.data.datasource.RemoteMockyDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteMockyDataSource(
        api: FootballService
    ): RemoteMockyDataSource {
        return MockyDataSource(api)
    }
}