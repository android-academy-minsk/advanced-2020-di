package minsk.androidacademy.githubclient.feature.splash.data.remote

import minsk.androidacademy.githubclient.feature.splash.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubUserEndpoint {

    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): UserResponse
}