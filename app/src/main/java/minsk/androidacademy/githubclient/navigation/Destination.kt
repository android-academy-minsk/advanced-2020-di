package minsk.androidacademy.githubclient.navigation

import minsk.androidacademy.githubclient.feature.splash.UserLogin

sealed class Destination {

    object Back : Destination()

    data class RepositoryList(val userLogin: UserLogin) : Destination()
}