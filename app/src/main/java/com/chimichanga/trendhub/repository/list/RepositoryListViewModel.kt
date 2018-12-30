package com.chimichanga.trendhub.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chimichanga.trendhub.common.model.Owner
import com.chimichanga.trendhub.common.model.Repository
import javax.inject.Inject

class RepositoryListViewModel @Inject constructor() : ViewModel() {

    val trendingRepositories: LiveData<List<Repository>> by lazy {
        MutableLiveData<List<Repository>>().apply {
            postValue(listOf(
                Repository(
                    id = 1000,
                    description = "Pleasant Android application development",
                    fullname = "Kotlin/anko",
                    owner = Owner("https://avatars2.githubusercontent.com/u/1446536?s=200&v=4"),
                    stars = 13195,
                    forks = 1045
                ))
            )
        }
    }

}
