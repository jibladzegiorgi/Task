package com.giorgi.jibladze.football.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ImpRetrofitCallback<P> : Callback<P> {

    override fun onFailure(call: Call<P>, t: Throwable) {
        Log.d("onFailure",t.message)
        error(t.message)
    }

    override fun onResponse(call: Call<P>, response: Response<P>) {
        if (response.isSuccessful) {
            successLoadData(response.body())
        }
    }

    abstract fun successLoadData(body: P?)
    abstract fun error(m:String?)
}