package com.giorgi.jibladze.football.di

import android.content.Context
import com.giorgi.jibladze.football.network.FootballService
import com.giorgi.jibladze.football.network.data.MockyDataSource
import com.giorgi.jibladze.football.network.data.RemoteMockyDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
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