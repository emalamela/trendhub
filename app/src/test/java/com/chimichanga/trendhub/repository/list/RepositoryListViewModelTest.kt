package com.chimichanga.trendhub.repository.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.chimichanga.trendhub.common.data.Error
import com.chimichanga.trendhub.common.data.Loading
import com.chimichanga.trendhub.common.data.Resource
import com.chimichanga.trendhub.common.data.Success
import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.common.pagination.Page
import com.chimichanga.trendhub.repository.RepositoryDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception
import java.lang.RuntimeException

class RepositoryListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Suppress("EXPERIMENTAL_API_USAGE")
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    @ObsoleteCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `view model loads and posts trending repositories successfully`() {
        val lifecycle = mock(LifecycleOwner::class.java)!!
        val lifecycleRegistry = LifecycleRegistry(lifecycle)
        lifecycleRegistry.apply {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
        `when`(lifecycle.lifecycle).thenReturn(lifecycleRegistry)

        val repositoryDataSource = mock(RepositoryDataSource::class.java)!!
        val trendingRepositories = Page(listOf(mock(Repository::class.java)!!, mock(Repository::class.java)!!))
        val trendingRepositoriesDeferred = CompletableDeferred(trendingRepositories)

        // TODO: Check why tests don't finish calling this Deferred
        `when`(repositoryDataSource.trendingRepositories).thenReturn(trendingRepositoriesDeferred)

        val valuesObserved = mutableListOf<Resource<List<Repository>>>()
        val viewModelToTest = RepositoryListViewModel(repositoryDataSource)
        viewModelToTest.trendingRepositories.observe(lifecycle, Observer {
            valuesObserved.add(it)
        })

        assert(valuesObserved.size == 2)
        assert(valuesObserved[0] is Loading<List<Repository>>)
        assert(valuesObserved[1] is Success<List<Repository>>)
        assert((valuesObserved[1] as Success<List<Repository>>).data == trendingRepositories)
    }

    @Test
    fun `view model tries to loads repositories but fails and reports errors`() {
        val lifecycle = mock(LifecycleOwner::class.java)!!
        val lifecycleRegistry = LifecycleRegistry(lifecycle)
        lifecycleRegistry.apply {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
        `when`(lifecycle.lifecycle).thenReturn(lifecycleRegistry)

        val repositoryDataSource = mock(RepositoryDataSource::class.java)!!
        // TODO: Check why tests don't finish calling this Deferred
        `when`(repositoryDataSource.trendingRepositories).thenThrow(RuntimeException())

        val valuesObserved = mutableListOf<Resource<List<Repository>>>()
        val viewModelToTest = RepositoryListViewModel(repositoryDataSource)
        viewModelToTest.trendingRepositories.observe(lifecycle, Observer {
            valuesObserved.add(it)
        })

        assert(valuesObserved.size == 2)
        assert(valuesObserved[0] is Loading<List<Repository>>)
        assert(valuesObserved[1] is Error<List<Repository>, *>)
    }

}