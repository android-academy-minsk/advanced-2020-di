package minsk.androidacademy.githubclient.feature.repos.data.remote

import minsk.androidacademy.githubclient.feature.repos.data.remote.response.UserRepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubUserRepositoriesEndpoint {

    @GET("/users/{username}/repos")
    suspend fun getUsersRepositories(
        @Path("username") username: String
    ): List<UserRepositoryResponse>
}