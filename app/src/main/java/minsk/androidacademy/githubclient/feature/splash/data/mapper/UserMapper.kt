package minsk.androidacademy.githubclient.feature.splash.data.mapper

import minsk.androidacademy.githubclient.extensions.orZero
import minsk.androidacademy.githubclient.feature.splash.data.remote.response.UserResponse
import minsk.androidacademy.githubclient.feature.splash.domain.model.User

internal interface UserMapper {

    fun map(user: UserResponse): User

    class Impl constructor() : UserMapper {

        override fun map(user: UserResponse): User {
            return with(user) { User(login, id.orZero(), url.orEmpty(), email.orEmpty()) }
        }
    }
}