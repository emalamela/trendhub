package com.chimichanga.trendhub.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.common.pagination.Page
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryListViewModel @Inject constructor(githubWebService: GithubWebService) : ViewModel() {

    val trendingRepositories: LiveData<List<Repository>> by lazy {
        val field = MutableLiveData<List<Repository>>()
        githubWebService.fetchAndroidTrendingRepositories()
            .enqueue(object : Callback<Page<Repository>> {
                override fun onResponse(call: Call<Page<Repository>>, response: Response<Page<Repository>>) {
                    field.postValue(response.body()!!.items)
                }

                override fun onFailure(call: Call<Page<Repository>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        field
    }

}
