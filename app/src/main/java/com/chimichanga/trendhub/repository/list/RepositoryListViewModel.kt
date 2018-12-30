package com.chimichanga.trendhub.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chimichanga.trendhub.common.data.Error
import com.chimichanga.trendhub.common.data.Loading
import com.chimichanga.trendhub.common.data.Resource
import com.chimichanga.trendhub.common.data.Success
import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.repository.RepositoryDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RepositoryListViewModel @Inject constructor(private val repositoryDataSource: RepositoryDataSource) :
    ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val trendingRepositories: LiveData<Resource<List<Repository>>> by lazy {
        MutableLiveData<Resource<List<Repository>>>().apply {
            launch(context = coroutineContext) {
                postValue(Loading())

                try {
                    postValue(Success(repositoryDataSource.trendingRepositories.await().items))
                } catch (e: Exception) {
                    postValue(Error(e))
                }
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
