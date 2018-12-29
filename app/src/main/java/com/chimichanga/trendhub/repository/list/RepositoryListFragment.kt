package com.chimichanga.trendhub.repository.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chimichanga.trendhub.R

class RepositoryListFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoryListFragment()
    }

    private lateinit var viewModel: RepositoryListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepositoryListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
