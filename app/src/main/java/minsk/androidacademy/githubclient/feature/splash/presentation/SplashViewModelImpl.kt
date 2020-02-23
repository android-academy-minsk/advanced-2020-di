package minsk.androidacademy.githubclient.feature.splash.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import minsk.androidacademy.githubclient.extensions.exhaustive
import minsk.androidacademy.githubclient.feature.splash.domain.interactor.GetUserLoginUseCase
import minsk.androidacademy.githubclient.feature.splash.domain.model.EmptyUserName
import minsk.androidacademy.githubclient.feature.splash.domain.model.NetworkConnection
import minsk.androidacademy.githubclient.feature.splash.domain.model.NotFoundException
import minsk.androidacademy.githubclient.feature.splash.domain.model.Success
import minsk.androidacademy.githubclient.feature.splash.domain.model.UserResult
import minsk.androidacademy.githubclient.navigation.Destination.RepositoryList
import minsk.androidacademy.githubclient.navigation.Router

internal class SplashViewModelImpl constructor(
    private val router: Router,
    private val getUserLoginUseCase: GetUserLoginUseCase
) : SplashViewModel() {

    override val showErrorOutput = MutableLiveData<String>()
    override val showLoadingOutput = MutableLiveData<Unit>()
    override val hideLoadingOutput = MutableLiveData<Unit>()
    override val showShowRepoOutput = MutableLiveData<Unit>()
    override val hideShowRepoOutput = MutableLiveData<Unit>()

    override fun onShowRepositoriesClick(userName: String) {
        viewModelScope.launch {
            showLoadingOutput.value = Unit
            hideShowRepoOutput.value = Unit

            val userResult = getUserLoginUseCase.getUser(userName)

            hideLoadingOutput.value = Unit
            showShowRepoOutput.value = Unit
            handleResult(userResult)
        }
    }

    private fun handleResult(userResult: UserResult) {
        when (userResult) {
            is Success -> router.goto(RepositoryList(userResult.user.login))
            NetworkConnection -> showErrorOutput.value = "Network exception"
            NotFoundException -> showErrorOutput.value = "Not Found user exception"
            EmptyUserName -> showErrorOutput.value = "Enter user name"
        }.exhaustive
    }
}