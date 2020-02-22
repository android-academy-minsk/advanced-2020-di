package minsk.androidacademy.githubclient.feature.repos.domain.interactor

import minsk.androidacademy.githubclient.feature.repos.domain.UserRepositoriesRepository
import minsk.androidacademy.githubclient.feature.repos.presentation.model.UserRepositoriesResult
import minsk.androidacademy.githubclient.feature.splash.UserLogin
import javax.inject.Inject

internal interface GetUserRepositoriesUseCase {

    suspend fun getUserRepositories(userLogin: UserLogin): UserRepositoriesResult

    class Impl @Inject constructor(
        private val repository: UserRepositoriesRepository
    ) : GetUserRepositoriesUseCase {

        override suspend fun getUserRepositories(userLogin: UserLogin): UserRepositoriesResult {
            return repository.getUserRepositories(userLogin)
        }
    }
}