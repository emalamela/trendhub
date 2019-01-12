package com.chimichanga.trendhub.repository.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.chimichanga.trendhub.R
import com.chimichanga.trendhub.common.di.factory.ViewModelFactory
import com.chimichanga.trendhub.common.glide.GlideApp
import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.repository.util.formatRepositoryName
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri


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
        with(repository) {
            GlideApp
                .with(requireContext())
                .load(owner.avatarUrl)
                .fitCenter()
                .into(repositoryDetailAvatar)

            repositoryDetailFullName.text = formatRepositoryName(fullname)
            repositoryDetailFullName.setOnClickListener {
                // Launch Browser URL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(repository.url)
                val title = resources.getText(R.string.repository_detail_url_chooser)
                startActivity(Intent.createChooser(intent, title))
            }
            repositoryDetailDescription.text = description
            repositoryDetailLanguage.visibility = if (language != null) View.VISIBLE else View.GONE
            repositoryDetailLanguage.text = language
            repositoryDetailStars.text = stars.toString()
            repositoryDetailForks.text = forks.toString()
        }
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