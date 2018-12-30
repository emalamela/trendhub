package com.chimichanga.trendhub.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chimichanga.trendhub.common.model.Repository
import javax.inject.Inject

class RepositoryDetailViewModel @Inject constructor() : ViewModel() {

    private val mutableRepositoryToDetail = MutableLiveData<Repository>()
    val repositoryToDetail: LiveData<Repository> = mutableRepositoryToDetail

    fun setRepositoryToDetail(repository: Repository) {
        mutableRepositoryToDetail.postValue(repository)
    }

}