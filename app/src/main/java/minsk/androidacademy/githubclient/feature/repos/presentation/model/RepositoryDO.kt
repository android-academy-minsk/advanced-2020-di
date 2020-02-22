package minsk.androidacademy.githubclient.feature.repos.presentation.model

data class RepositoryDO(
    val type: Type,
    val name: String,
    val stargazersCount: String,
    val forksCount: String
)

enum class Type(val type: Int) {
    TYPE_NORMAL(0),
    TYPE_BIG(1),
    TYPE_FEATURED(2);
}