package com.chimichanga.trendhub.repository.list

import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.common.pagination.Page
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubWebService {

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories?q=topic:android&page=1&per_page=30")
    fun fetchAndroidTrendingRepositories(): Deferred<Page<Repository>>

}