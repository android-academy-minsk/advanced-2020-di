package minsk.androidacademy.githubclient.feature.splash.data

import minsk.androidacademy.githubclient.feature.splash.data.mapper.UserMapper
import minsk.androidacademy.githubclient.feature.splash.data.remote.GithubUserEndpoint
import minsk.androidacademy.githubclient.feature.splash.domain.UserRepository
import minsk.androidacademy.githubclient.feature.splash.domain.model.NetworkConnection
import minsk.androidacademy.githubclient.feature.splash.domain.model.NotFoundException
import minsk.androidacademy.githubclient.feature.splash.domain.model.Success
import minsk.androidacademy.githubclient.feature.splash.domain.model.UserResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val remoteApi: GithubUserEndpoint,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUser(userName: String): UserResult {
        return try {
            val user = remoteApi.getUser(userName)
            return Success(userMapper.map(user))
        } catch (exception: IOException) {
            NetworkConnection
        } catch (exception: HttpException) {
            NotFoundException
        }
    }
}