package com.chimichanga.trendhub.repository.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.chimichanga.trendhub.R
import com.chimichanga.trendhub.common.di.factory.ViewModelFactory
import com.chimichanga.trendhub.common.model.Repository
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RepositoryDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { viewModelFactory.create(RepositoryDetailViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setRepositoryToDetail(arguments!!.getSerializable(REPOSITORY_TO_DETAIL_EXTRA) as Repository)

        viewModel.repositoryToDetail.observe(this, Observer { displayRepository(it) })
    }

    private fun displayRepository(repository: Repository) {
        // TODO: Implement
    }

    companion object {

        private const val REPOSITORY_TO_DETAIL_EXTRA = "repository_to_detail"

        fun newInstance(repository: Repository) = RepositoryDetailFragment().apply {
            val args = Bundle()
            args.putSerializable(REPOSITORY_TO_DETAIL_EXTRA, repository)
            arguments = args
        }

    }

}