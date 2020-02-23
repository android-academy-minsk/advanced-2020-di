package minsk.androidacademy.githubclient.feature.repos.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_repositories_list.rvRepositories
import kotlinx.android.synthetic.main.activity_splash.pbLoading
import minsk.androidacademy.githubclient.R
import minsk.androidacademy.githubclient.extensions.subscribe
import minsk.androidacademy.githubclient.feature.repos.di.model.UserLoginIdentifier
import minsk.androidacademy.githubclient.feature.repos.di.userRepositoriesBindingKodeinModule
import minsk.androidacademy.githubclient.feature.repos.di.userRepositoriesModule
import minsk.androidacademy.githubclient.feature.repos.presentation.RepositoriesListViewModel
import minsk.androidacademy.githubclient.feature.repos.presentation.model.RepositoryDO
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.RepositoriesListAdapter
import minsk.androidacademy.githubclient.feature.splash.UserLogin
import minsk.androidacademy.githubclient.mvp.getViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

private const val EXTRA_USER_LOGIN = "extra.user_login"

class RepositoriesListActivity : AppCompatActivity(), KodeinAware {

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

    override lateinit var kodein: Kodein

    private fun initKodein() {
        kodein = Kodein {
            extend((application as KodeinAware).kodein)
            import(userRepositoriesBindingKodeinModule)
            import(userRepositoriesModule)
            bind<Context>() with provider { this@RepositoriesListActivity }
            bind<UserLoginIdentifier>() with provider {
                UserLoginIdentifier(intent.getStringExtra(EXTRA_USER_LOGIN).orEmpty())
            }
        }
    }

    private val viewModel: RepositoriesListViewModel by getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKodein()
        setContentView(R.layout.activity_repositories_list)

        setupRepositoriesListView()

        subscribe(viewModel.hideLoadingOutput) { hideLoading() }
        subscribe(viewModel.showLoadingOutput) { showLoading() }
        subscribe(viewModel.repositoriesListOutput, ::setRepositories)

        viewModel.loadRepositories()
    }

    private fun setupRepositoriesListView() {
        val repositoriesListAdapter: RepositoriesListAdapter by instance()
        val linearLayoutManager: LinearLayoutManager by instance()

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
        val repositoriesListAdapter: RepositoriesListAdapter by instance()

        repositoriesListAdapter.updateRepositoriesList(repositories)
    }
}