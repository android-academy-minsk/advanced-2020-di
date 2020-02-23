package minsk.androidacademy.githubclient.feature.splash.domain.interactor

import minsk.androidacademy.githubclient.feature.splash.domain.UserRepository
import minsk.androidacademy.githubclient.feature.splash.domain.model.EmptyUserName
import minsk.androidacademy.githubclient.feature.splash.domain.model.UserResult

internal interface GetUserLoginUseCase {

    suspend fun getUser(userName: String): UserResult

    class Impl constructor(
        private val repository: UserRepository
    ) : GetUserLoginUseCase {

        override suspend fun getUser(userName: String): UserResult {
            if (userName.isEmpty()) {
                return EmptyUserName
            }

            return repository.getUser(userName)
        }
    }
}