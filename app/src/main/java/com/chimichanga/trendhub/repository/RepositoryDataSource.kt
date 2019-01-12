package com.chimichanga.trendhub.repository

import com.chimichanga.trendhub.repository.list.GithubWebService
import com.chimichanga.trendhub.common.pagination.Page
import com.chimichanga.trendhub.common.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Data source for Github's trending Android repositories.
 *
 * Repository pattern for [Repository]. The name 'Repository' was taken for the model, 'DataSource' was chosen instead.
 */
@Singleton
class RepositoryDataSource @Inject constructor(private val githubWebService: GithubWebService) {

    /**
     * [kotlinx.coroutines.Deferred] that yields a [Page] of [Repository] if successful.
     */
    val trendingRepositories
        get() = githubWebService.fetchAndroidTrendingRepositories()

}