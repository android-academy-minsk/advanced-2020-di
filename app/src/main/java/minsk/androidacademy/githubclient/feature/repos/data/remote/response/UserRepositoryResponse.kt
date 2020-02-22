package minsk.androidacademy.githubclient.feature.repos.data.remote.response

import com.google.gson.annotations.SerializedName

internal data class UserRepositoryResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("watchers_count")
    val watchersCount: Int?,
    @SerializedName("forks_count")
    var forksCount: Int?,
    @SerializedName("forks")
    var forks: Int?,
    @SerializedName("open_issues")
    var openIssues: Int?,
    @SerializedName("watchers")
    val watchers: Int?
)