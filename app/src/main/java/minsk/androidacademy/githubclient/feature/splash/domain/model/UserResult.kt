package minsk.androidacademy.githubclient.feature.splash.domain.model

internal sealed class UserResult

internal data class Success(val user: User) : UserResult()

internal sealed class Failure : UserResult()

internal object NetworkConnection : Failure()
internal object NotFoundException : Failure()
internal object EmptyUserName : Failure()