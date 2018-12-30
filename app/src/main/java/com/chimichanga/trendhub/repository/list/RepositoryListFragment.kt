package com.chimichanga.trendhub.repository.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chimichanga.trendhub.R
import com.chimichanga.trendhub.common.model.Owner
import com.chimichanga.trendhub.common.model.Repository
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryListFragment : Fragment() {

    private lateinit var viewModel: RepositoryListViewModel
    private val adapter: RepositoryListAdapter by lazy { RepositoryListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepositoryListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoryListRecycler.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }

        // TODO: Use real data
        adapter.submitList(listOf(
            Repository(
                id = 1000,
                description = "Pleasant Android application development",
                fullname = "Kotlin/anko",
                owner = Owner("https://avatars2.githubusercontent.com/u/1446536?s=200&v=4"),
                stars = 13195,
                forks = 1045)
        ))
    }

    companion object {
        fun newInstance() = RepositoryListFragment()
    }
}
