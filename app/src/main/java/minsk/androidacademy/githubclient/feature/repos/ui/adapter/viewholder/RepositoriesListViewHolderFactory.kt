package minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder

import android.view.ViewGroup

interface RepositoriesListViewHolderFactory {

    fun createViewHolder(parent: ViewGroup): RepositoryViewHolder

    class Impl constructor() : RepositoriesListViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
            return RepositoryViewHolderFeatured(parent)
        }
    }
}

class RepositoryViewHolderFeaturedFactory constructor() :
    RepositoriesListViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
        return RepositoryViewHolderFeatured(parent)
    }
}

class RepositoryViewHolderBigFactory constructor() : RepositoriesListViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
        return RepositoryViewHolderBig(parent)
    }
}

class RepositoryViewHolderNormalFactory constructor() : RepositoriesListViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup): RepositoryViewHolder {
        return RepositoryViewHolderNormal(parent)
    }
}