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

private const val API_KEY = "8d9b685b8524b18b291492e2ecc72e1e"
private const val BASE_URL = "https://api.themoviedb.org/3"