package minsk.androidacademy.githubclient.feature.repos.data.remote.mapper

import minsk.androidacademy.githubclient.extensions.orZero
import minsk.androidacademy.githubclient.feature.repos.data.remote.response.UserRepositoryResponse
import minsk.androidacademy.githubclient.feature.repos.domain.model.Repository

internal interface UserRepositoryMapper {

    fun map(response: UserRepositoryResponse): Repository

    class Impl constructor() : UserRepositoryMapper {
        override fun map(response: UserRepositoryResponse): Repository {
            return Repository(
                name = response.name.orEmpty(),
                stargazersCount = response.stargazersCount.orZero(),
                forksCount = response.forksCount.orZero()
            )
        }
    }
}