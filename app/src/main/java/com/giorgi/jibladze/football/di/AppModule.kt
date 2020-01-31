package com.giorgi.jibladze.football.di

import com.giorgi.jibladze.football.BuildConfig
import com.giorgi.jibladze.football.network.FootballService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//class AppModule {
//
//    @Singleton
//    @Provides
//    fun provideFootballService(
//        retrofit: Retrofit
//    ): FootballService =
//        retrofit.create(FootballService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(
//        url: String,
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .baseUrl(url)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideBaseUrlForOauth() = FootballService.BASE_URL
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClientMain(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//        val okHttpClientBuilder = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val builder = chain.request().newBuilder()
//                    .addHeader("Content-Type", "application/json")
//                chain.proceed(builder.build())
//            }
//            .readTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, TimeUnit.SECONDS)
//        if (BuildConfig.DEBUG)
//            okHttpClientBuilder.addInterceptor(logging)
//        return okHttpClientBuilder.build()
//    }
//
//}