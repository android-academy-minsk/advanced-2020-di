package minsk.androidacademy.githubclient.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_splash.btnShowRepositories
import kotlinx.android.synthetic.main.activity_splash.etUsername
import kotlinx.android.synthetic.main.activity_splash.pbLoading
import minsk.androidacademy.githubclient.GithubClientApplication
import minsk.androidacademy.githubclient.R
import minsk.androidacademy.githubclient.extensions.subscribe
import minsk.androidacademy.githubclient.feature.splash.di.FeatureSplashComponent.Factory
import minsk.androidacademy.githubclient.feature.splash.presentation.SplashViewModel
import minsk.androidacademy.githubclient.mvp.ViewModelFactory
import minsk.androidacademy.githubclient.mvp.getViewModel
import minsk.androidacademy.githubclient.navigation.Router
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    internal lateinit var factory: ViewModelFactory

    @Inject
    internal lateinit var router: Router

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Factory.get(GithubClientApplication[this].appComponent)
            .inject(this)

        viewModel = getViewModel(factory)

        etUsername.addTextChangedListener(onTextChanged = { _, _, _, _ -> etUsername.error = null })
        btnShowRepositories.setOnClickListener {
            viewModel.onShowRepositoriesClick(etUsername.text.toString())
        }

        subscribe(viewModel.hideLoadingOutput) { hideLoading() }
        subscribe(viewModel.showLoadingOutput) { showLoading() }
        subscribe(viewModel.showErrorOutput, ::showValidationError)
        subscribe(viewModel.showShowRepoOutput) { showShowRepoBtn() }
        subscribe(viewModel.hideShowRepoOutput) { hideShowRepoBtn() }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        router.setNavigator(SplashNavigator(this))
    }

    override fun onPause() {
        super.onPause()

        router.setNavigator(null)
    }

    private fun showValidationError(error: String) {
        pbLoading.visibility = View.GONE
        etUsername.error = error
    }

    private fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pbLoading.visibility = View.GONE
    }

    private fun showShowRepoBtn() {
        btnShowRepositories.visibility = View.VISIBLE
    }

    private fun hideShowRepoBtn() {
        btnShowRepositories.visibility = View.GONE
    }
}