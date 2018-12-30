package com.chimichanga.trendhub.repository.list

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chimichanga.trendhub.R
import com.chimichanga.trendhub.common.glide.GlideApp
import com.chimichanga.trendhub.common.model.Repository
import com.chimichanga.trendhub.repository.ui.formatRepositoryName
import kotlinx.android.synthetic.main.item_repository.view.*

typealias OnRepositoryClicked = (Repository) -> Unit

class RepositoryListAdapter(private val onRepositoryClicked: OnRepositoryClicked) :
    ListAdapter<Repository, RepositoryViewHolder>(RepositoryItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryViewHolder(parent)

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) =
        holder.bind(getItem(position), onRepositoryClicked)

}

private object RepositoryItemCallback : ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
}

class RepositoryViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)) {

    fun bind(item: Repository, onRepositoryClicked: OnRepositoryClicked) {

        itemView.apply {
            repositoryFullName.text = formatRepositoryName(item.fullname)
            repositoryDescription.text = item.description
            repositoryStars.text = item.stars.toString()
            repositoryForks.text = item.forks.toString()

            GlideApp
                .with(context)
                .load(item.owner.avatarUrl)
                .placeholder(R.color.repository_description_text_color)
                .centerCrop()
                .into(repositoryOwnerAvatar)

            setOnClickListener { onRepositoryClicked(item) }
        }
    }

}
