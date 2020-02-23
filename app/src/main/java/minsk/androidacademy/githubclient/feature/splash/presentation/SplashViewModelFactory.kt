package minsk.androidacademy.githubclient.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import minsk.androidacademy.githubclient.feature.splash.domain.interactor.GetUserLoginUseCase
import minsk.androidacademy.githubclient.navigation.Router

internal class SplashViewModelFactory constructor(
    private val router: Router,
    private val getUserLoginUseCase: GetUserLoginUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModelImpl(router, getUserLoginUseCase) as T
    }
}