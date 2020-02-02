package com.giorgi.jibladze.football.network

import com.giorgi.jibladze.football.model.MockyResult
import retrofit2.Call
import retrofit2.http.GET

interface FootballService {

    companion object {
        const val BASE_URL = "https://www.mocky.io/"
    }

//    http://www.mocky.io/v2/5b9264193300006b00205fb9

    @GET("v2/5b9264193300006b00205fb9")
    fun getMockyResult(): Call<MockyResult>
}