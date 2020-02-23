package minsk.androidacademy.githubclient.feature.repos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import minsk.androidacademy.githubclient.feature.repos.di.model.UserLoginIdentifier
import minsk.androidacademy.githubclient.feature.repos.domain.interactor.GetUserRepositoriesUseCase
import minsk.androidacademy.githubclient.feature.repos.presentation.mapper.RepositoryMapper
import minsk.androidacademy.githubclient.feature.repos.presentation.model.NetworkConnection
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Success
import minsk.androidacademy.githubclient.feature.repos.presentation.model.UserRepositoriesResult

internal class RepositoriesListViewModelImpl constructor(
    private val userLoginIdentifier: UserLoginIdentifier,
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase,
    private val mapper: RepositoryMapper
) : RepositoriesListViewModel() {

    override val showLoadingOutput = MutableLiveData<Unit>()
    override val hideLoadingOutput = MutableLiveData<Unit>()
    override val repositoriesListOutput = MutableLiveData<List<RepositoryDO>>()

    override fun loadRepositories() {
        viewModelScope.launch {
            showLoadingOutput.value = Unit

            val userReposResult = getUserRepositoriesUseCase.getUserRepositories(
                userLoginIdentifier.userLogin
            )

            hideLoadingOutput.value = Unit
            handleResult(userReposResult)
        }
    }

    private fun handleResult(userReposResult: UserRepositoriesResult) {
        when (userReposResult) {
            is Success -> {
                val repos = userReposResult.repos.map { repo -> mapper.map(repo) }
                repositoriesListOutput.value = repos
            }
            NetworkConnection -> {
            }
        }
    }
}