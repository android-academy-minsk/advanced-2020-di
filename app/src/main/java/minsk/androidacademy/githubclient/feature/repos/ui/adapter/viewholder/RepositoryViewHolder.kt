package minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO

abstract class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(repository: RepositoryDO)
}