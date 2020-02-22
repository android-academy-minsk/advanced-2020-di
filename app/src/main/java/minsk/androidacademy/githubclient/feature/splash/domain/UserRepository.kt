package minsk.androidacademy.githubclient.feature.splash.domain

import minsk.androidacademy.githubclient.feature.splash.domain.model.UserResult

internal interface UserRepository {

    suspend fun getUser(userName: String): UserResult
}