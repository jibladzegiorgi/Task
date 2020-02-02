package com.giorgi.jibladze.football.di

import com.giorgi.jibladze.football.BuildConfig
import com.giorgi.jibladze.football.network.FootballService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFootballService(
        retrofit: Retrofit
    ): FootballService =
        retrofit.create(FootballService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        url: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(url)
            .build()
    }

    @Provides
    fun provideBaseUrlForOauth() = FootballService.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClientMain(): OkHttpClient {

        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }


            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor { chain ->
                val b = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                chain.proceed(b.build())
            }
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG)
                builder.addInterceptor(logging)

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

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
    }

}