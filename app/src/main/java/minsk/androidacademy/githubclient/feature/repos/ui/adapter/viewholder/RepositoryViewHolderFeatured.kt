package minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import minsk.androidacademy.githubclient.R
import minsk.androidacademy.githubclient.R.layout
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO

class RepositoryViewHolderFeatured(parent: ViewGroup) : RepositoryViewHolder(
    LayoutInflater.from(parent.context).inflate(
        layout.list_item_featured,
        parent,
        false
    )
) {

    private val tvName: TextView = itemView.findViewById(R.id.tvName)
    private val tvStars: TextView = itemView.findViewById(R.id.tvStars)
    private val tvForks: TextView = itemView.findViewById(R.id.tvForks)

    override fun bind(repository: RepositoryDO) {
        tvName.text = repository.name
        tvStars.text = repository.stargazersCount
        tvForks.text = repository.forksCount
    }
}