package com.chimichanga.trendhub.repository.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import com.chimichanga.trendhub.common.model.Repository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@RunWith(JUnit4::class)
class RepositoryDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `empty view model has no repository to detail`() {
        assert(RepositoryDetailViewModel().repositoryToDetail.value == null)
    }

    @Test
    fun `set repository to detail and get that same repository as value`() {
        val repositoryToDetail = mock(Repository::class.java)!!
        val repositoryViewModelToTest = RepositoryDetailViewModel()
        repositoryViewModelToTest.setRepositoryToDetail(repositoryToDetail)

        assert(repositoryViewModelToTest.repositoryToDetail.value == repositoryToDetail)
    }

    @Test
    fun `set repository to detail and observe that same repository as value`() {
        val lifecycle = mock(LifecycleOwner::class.java)!!
        val lifecycleRegistry = LifecycleRegistry(lifecycle)
        lifecycleRegistry.apply {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        `when`(lifecycle.lifecycle).thenReturn(lifecycleRegistry)

        val repositoryToDetail = mock(Repository::class.java)!!
        val repositoryViewModelToTest = RepositoryDetailViewModel()
        repositoryViewModelToTest.setRepositoryToDetail(repositoryToDetail)

        repositoryViewModelToTest.repositoryToDetail.observe(lifecycle, Observer<Repository> {
            assert(it == repositoryToDetail)
        })
    }

}