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
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryListAdapter : ListAdapter<Repository, RepositoryViewHolder>(RepositoryItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryViewHolder(parent)

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) = holder.bind(getItem(position))

}

private object RepositoryItemCallback : ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
}

class RepositoryViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)) {

    fun bind(item: Repository) {
        itemView.apply {
            repositoryFullName.text = spanRepositoryName(item.fullname)
            repositoryDescription.text = item.description
            repositoryStars.text = item.stars.toString()
            repositoryForks.text = item.forks.toString()

            GlideApp
                .with(context)
                .load(item.owner.avatarUrl)
                .placeholder(R.color.repository_description_text_color)
                .centerCrop()
                .into(repositoryOwnerAvatar)
        }
    }

    private fun spanRepositoryName(name: String): Spannable {
        val nameSplit = name.split("/")
        val nameToSpan = "${nameSplit[0]} / ${nameSplit[1]}"
        return SpannableString(nameToSpan).apply {
            setSpan(
                StyleSpan(Typeface.BOLD),
                nameToSpan.lastIndexOf(' '), nameToSpan.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

}
