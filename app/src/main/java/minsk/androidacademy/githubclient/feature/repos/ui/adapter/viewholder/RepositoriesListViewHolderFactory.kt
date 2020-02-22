package minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder

import android.view.ViewGroup
import javax.inject.Inject

interface RepositoriesListViewHolderFactory {

    fun createViewHolder(parent: ViewGroup): RepositoryViewHolder

    class Impl @Inject constructor() : RepositoriesListViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
            return RepositoryViewHolderFeatured(parent)
        }
    }
}

class RepositoryViewHolderFeaturedFactory @Inject constructor() :
    RepositoriesListViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
        return RepositoryViewHolderFeatured(parent)
    }
}

class RepositoryViewHolderBigFactory @Inject constructor() : RepositoriesListViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
        return RepositoryViewHolderBig(parent)
    }
}

class RepositoryViewHolderNormalFactory @Inject constructor() : RepositoriesListViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
        return RepositoryViewHolderNormal(parent)
    }
}