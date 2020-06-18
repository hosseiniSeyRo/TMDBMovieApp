package com.parsdroid.tmdbmovieapp.di

import com.parsdroid.tmdbmovieapp.data.TMDBApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $API_KEY")
                .build()
            chain.proceed(request)
        }

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideTMDBApi(okHttpClient: OkHttpClient): TMDBApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TMDBApi::class.java)
    }
}

private const val API_KEY =
//    "8d9b685b8524b18b291492e2ecc72e1e"
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4ZDliNjg1Yjg1MjRiMThiMjkxNDkyZTJlY2M3MmUxZSIsInN1YiI6IjU5N2Y1MWI2OTI1MTQxNWQ1ZDAzMzJmMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qWmgoRGDoGQ4noTJIwu03-ScYgck3I0f7Y_3YC6DTHk"
private const val BASE_URL = "https://api.themoviedb.org/3/"