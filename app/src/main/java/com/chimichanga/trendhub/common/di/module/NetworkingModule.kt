package com.chimichanga.trendhub.common.di.module

import com.chimichanga.trendhub.repository.list.GithubWebService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
abstract class NetworkingModule {

    @Module
    companion object {
        private const val GITHUB_API_BASE_URL = "https://api.github.com/"

        @JvmStatic
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideGithubRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GITHUB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideGithubWebService(githubRetrofit: Retrofit): GithubWebService {
            return githubRetrofit.create(GithubWebService::class.java)
        }
    }


}