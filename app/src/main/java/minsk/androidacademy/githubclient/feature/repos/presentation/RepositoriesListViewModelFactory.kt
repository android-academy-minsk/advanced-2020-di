package minsk.androidacademy.githubclient.feature.repos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import minsk.androidacademy.githubclient.feature.repos.di.model.UserLoginIdentifier
import minsk.androidacademy.githubclient.feature.repos.domain.interactor.GetUserRepositoriesUseCase
import minsk.androidacademy.githubclient.feature.repos.presentation.mapper.RepositoryMapper

internal class RepositoriesListViewModelFactory constructor(
    private val userLoginIdentifier: UserLoginIdentifier,
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase,
    private val mapper: RepositoryMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoriesListViewModelImpl(
            userLoginIdentifier,
            getUserRepositoriesUseCase,
            mapper
        ) as T
    }
}