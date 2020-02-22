package minsk.androidacademy.githubclient.feature.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal abstract class SplashViewModel : ViewModel() {

    abstract val showLoadingOutput: LiveData<Unit>
    abstract val hideLoadingOutput: LiveData<Unit>
    abstract val showShowRepoOutput: LiveData<Unit>
    abstract val hideShowRepoOutput: LiveData<Unit>
    abstract val showErrorOutput: LiveData<String>

    abstract fun onShowRepositoriesClick(userName: String)
}