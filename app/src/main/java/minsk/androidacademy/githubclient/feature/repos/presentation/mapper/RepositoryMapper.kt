package minsk.androidacademy.githubclient.feature.repos.presentation.mapper

import minsk.androidacademy.githubclient.feature.repos.domain.model.Repository
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type.TYPE_BIG
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type.TYPE_FEATURED
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type.TYPE_NORMAL

internal interface RepositoryMapper {

    fun map(repository: Repository): RepositoryDO

    class Impl constructor() : RepositoryMapper {

        override fun map(repository: Repository): RepositoryDO {
            val type = getPresentationType(repository)
            val starsText = "Stars: ${repository.stargazersCount}"
            val forksText = "Forks: ${repository.stargazersCount}"

            return RepositoryDO(
                type = type,
                name = repository.name,
                stargazersCount = starsText,
                forksCount = forksText
            )
        }

        private fun getPresentationType(repository: Repository): Type {
            return if (repository.stargazersCount > 500) {
                if (repository.forksCount > 100) {
                    TYPE_FEATURED
                } else {
                    TYPE_BIG
                }
            } else {
                TYPE_NORMAL
            }
        }
    }
}