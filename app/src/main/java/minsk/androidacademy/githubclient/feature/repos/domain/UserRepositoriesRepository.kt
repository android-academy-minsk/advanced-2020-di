package minsk.androidacademy.githubclient.feature.repos.domain

import minsk.androidacademy.githubclient.feature.repos.presentation.model.UserRepositoriesResult
import minsk.androidacademy.githubclient.feature.splash.UserLogin

internal interface UserRepositoriesRepository {

    suspend fun getUserRepositories(userLogin: UserLogin): UserRepositoriesResult
}