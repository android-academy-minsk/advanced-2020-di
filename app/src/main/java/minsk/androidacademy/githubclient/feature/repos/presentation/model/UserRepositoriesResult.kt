package minsk.androidacademy.githubclient.feature.repos.presentation.model

import minsk.androidacademy.githubclient.feature.repos.domain.model.Repository

internal sealed class UserRepositoriesResult

internal data class Success(val repos: List<Repository>) : UserRepositoriesResult()

internal sealed class Failure : UserRepositoriesResult()

internal object NetworkConnection : Failure()