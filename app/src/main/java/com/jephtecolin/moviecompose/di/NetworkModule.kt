package com.jephtecolin.moviecompose.di

import com.jephtecolin.moviecompose.data.remote.AuthenticationInterceptor
import com.jephtecolin.moviecompose.data.remote.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(AuthenticationInterceptor())
        .build()

    @Provides
    @Singleton
    @TMDBRetrofit
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun tmdbServiceAPI(@TMDBRetrofit retrofit: Retrofit) = retrofit.create(TMDBService::class.java)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TMDBRetrofit