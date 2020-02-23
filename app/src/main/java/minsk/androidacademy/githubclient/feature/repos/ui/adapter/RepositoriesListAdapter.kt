package minsk.androidacademy.githubclient.feature.repos.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoriesListViewHolderFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolder
import org.kodein.di.DKodein
import org.kodein.di.generic.instance

class RepositoriesListAdapter constructor(
    private val kodein: DKodein
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    private val repositories = mutableListOf<RepositoryDO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val type = Type.values().find { type -> type.type == viewType }!!

        return kodein.instance<Type, RepositoriesListViewHolderFactory>(arg = type)
            .createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    override fun getItemViewType(position: Int): Int {
        val repository: RepositoryDO = repositories[position]

        return repository.type.type
    }

    fun updateRepositoriesList(repositories: List<RepositoryDO>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)

        notifyDataSetChanged()
    }
}