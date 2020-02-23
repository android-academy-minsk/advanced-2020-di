package minsk.androidacademy.githubclient.feature.repos.data

import minsk.androidacademy.githubclient.feature.repos.data.remote.GithubUserRepositoriesEndpoint
import minsk.androidacademy.githubclient.feature.repos.data.remote.mapper.UserRepositoryMapper
import minsk.androidacademy.githubclient.feature.repos.domain.UserRepositoriesRepository
import minsk.androidacademy.githubclient.feature.repos.presentation.model.NetworkConnection
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Success
import minsk.androidacademy.githubclient.feature.repos.presentation.model.UserRepositoriesResult
import minsk.androidacademy.githubclient.feature.splash.UserLogin
import java.io.IOException

internal class UserRepositoriesRepositoryImpl constructor(
    private val remoteApi: GithubUserRepositoriesEndpoint,
    private val userRepositoryMapper: UserRepositoryMapper
) : UserRepositoriesRepository {

    override suspend fun getUserRepositories(userLogin: UserLogin): UserRepositoriesResult {
        return try {
            val response = remoteApi.getUsersRepositories(userLogin)
            return Success(response.map { repo -> userRepositoryMapper.map(repo) })
        } catch (exception: IOException) {
            NetworkConnection
        }
    }
}