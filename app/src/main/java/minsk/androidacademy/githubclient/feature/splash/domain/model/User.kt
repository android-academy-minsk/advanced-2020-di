package minsk.androidacademy.githubclient.feature.splash.domain.model

internal data class User(
    val login: String,
    val id: Long,
    val url: String,
    val email: String
)