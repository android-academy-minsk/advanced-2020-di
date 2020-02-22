package minsk.androidacademy.githubclient.feature.repos.domain.model

internal data class Repository(
    val name: String,
    val stargazersCount: Int,
    val forksCount: Int
)