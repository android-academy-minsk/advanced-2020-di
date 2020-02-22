package minsk.androidacademy.githubclient.feature.repos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO

abstract class RepositoriesListViewModel : ViewModel() {

    abstract val showLoadingOutput: LiveData<Unit>
    abstract val hideLoadingOutput: LiveData<Unit>

    abstract val repositoriesListOutput: LiveData<List<RepositoryDO>>

    abstract fun loadRepositories()
}