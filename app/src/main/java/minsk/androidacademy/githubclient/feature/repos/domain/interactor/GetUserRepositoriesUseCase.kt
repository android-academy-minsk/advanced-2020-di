package minsk.androidacademy.githubclient.feature.repos.domain.interactor

import minsk.androidacademy.githubclient.feature.repos.domain.UserRepositoriesRepository
import minsk.androidacademy.githubclient.feature.repos.presentation.model.UserRepositoriesResult
import minsk.androidacademy.githubclient.feature.splash.UserLogin

internal interface GetUserRepositoriesUseCase {

    suspend fun getUserRepositories(userLogin: UserLogin): UserRepositoriesResult

    class Impl constructor(
        private val repository: UserRepositoriesRepository
    ) : GetUserRepositoriesUseCase {

        override suspend fun getUserRepositories(userLogin: UserLogin): UserRepositoriesResult {
            return repository.getUserRepositories(userLogin)
        }
    }
}