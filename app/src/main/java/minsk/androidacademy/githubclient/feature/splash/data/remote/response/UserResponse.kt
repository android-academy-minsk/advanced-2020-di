package minsk.androidacademy.githubclient.feature.splash.data.remote.response

import com.google.gson.annotations.SerializedName

internal data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("following")
    val following: Int?
)