package minsk.androidacademy.githubclient.feature.repos.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_repositories_list.rvRepositories
import kotlinx.android.synthetic.main.activity_splash.pbLoading
import minsk.androidacademy.githubclient.GithubClientApplication
import minsk.androidacademy.githubclient.R
import minsk.androidacademy.githubclient.extensions.subscribe
import minsk.androidacademy.githubclient.feature.repos.di.FeatureUserRepositoriesComponent
import minsk.androidacademy.githubclient.feature.repos.di.model.UserLoginIdentifier
import minsk.androidacademy.githubclient.feature.repos.presentation.RepositoriesListViewModel
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.RepositoriesListAdapter
import minsk.androidacademy.githubclient.feature.splash.UserLogin
import minsk.androidacademy.githubclient.mvp.ViewModelFactory
import minsk.androidacademy.githubclient.mvp.getViewModel
import javax.inject.Inject

private const val EXTRA_USER_LOGIN = "extra.user_login"

class RepositoriesListActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context, userLogin: UserLogin): Intent {
            return Intent(
                context,
                RepositoriesListActivity::class.java
            ).apply {
                putExtra(EXTRA_USER_LOGIN, userLogin)
            }
        }
    }

    @Inject
    internal lateinit var factory: ViewModelFactory
    @Inject
    internal lateinit var repositoriesListAdapter: RepositoriesListAdapter
    @Inject
    internal lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var viewModel: RepositoriesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repositories_list)

        val userLogin = intent.getStringExtra(EXTRA_USER_LOGIN).orEmpty()

        FeatureUserRepositoriesComponent.Factory.get(GithubClientApplication[this].appComponent)
            .userRepositoriesComponent()
            .create(this,
                UserLoginIdentifier(
                    userLogin
                )
            )
            .inject(this)

        setupRepositoriesListView()

        viewModel = getViewModel(factory)

        subscribe(viewModel.hideLoadingOutput) { hideLoading() }
        subscribe(viewModel.showLoadingOutput) { showLoading() }
        subscribe(viewModel.repositoriesListOutput, ::setRepositories)

        viewModel.loadRepositories()
    }

    private fun setupRepositoriesListView() {
        rvRepositories.adapter = repositoriesListAdapter
        rvRepositories.layoutManager = linearLayoutManager
    }

    private fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pbLoading.visibility = View.GONE
    }

    private fun setRepositories(repositories: List<RepositoryDO>) {
        repositoriesListAdapter.updateRepositoriesList(repositories)
    }
}