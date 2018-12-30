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
import com.chimichanga.trendhub.common.data.Success
import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.repository.detail.RepositoryDetailFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repository_list.*
import javax.inject.Inject

class RepositoryListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RepositoryListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RepositoryListViewModel::class.java)
    }

    private val adapter: RepositoryListAdapter by lazy { RepositoryListAdapter { displayDetail(it) } }

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

        viewModel.trendingRepositories.observe(this, Observer {
            when (it) {
                is Loading -> repositoryListRefreshLayout.isRefreshing = true
                is Success -> {
                    repositoryListRefreshLayout.isRefreshing = false
                    adapter.submitList(it.data)
                }
                is Error -> {
                    repositoryListRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), R.string.repository_list_error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun displayDetail(repository: Repository) {
        requireActivity().supportFragmentManager!!.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
            .add(R.id.mainContainer, RepositoryDetailFragment.newInstance(repository))
            .commit()
    }

    companion object {
        fun newInstance() = RepositoryListFragment()
    }
}
