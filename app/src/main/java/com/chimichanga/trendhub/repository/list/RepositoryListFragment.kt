package com.chimichanga.trendhub.repository.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chimichanga.trendhub.R
import com.chimichanga.trendhub.common.data.Loading
import com.chimichanga.trendhub.common.data.Resource
import com.chimichanga.trendhub.common.data.Success
import com.chimichanga.trendhub.common.model.Repository
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repository_list.*
import javax.inject.Inject

class RepositoryListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RepositoryListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RepositoryListViewModel::class.java)
    }

    private val adapter: RepositoryListAdapter by lazy { RepositoryListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoryListRecycler.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }

        repositoryListRefreshLayout.isEnabled = false

        viewModel.trendingRepositories.observe(this, Observer<Resource<List<Repository>>> {
            when (it) {
                is Loading -> repositoryListRefreshLayout.isRefreshing = true
                is Success -> {
                    repositoryListRefreshLayout.isRefreshing = false
                    adapter.submitList(it.data)
                }
                is Error -> {
                    repositoryListRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), "There was an error loading repositories, please try again", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        fun newInstance() = RepositoryListFragment()
    }
}
