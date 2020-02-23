package minsk.androidacademy.githubclient.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_splash.btnShowRepositories
import kotlinx.android.synthetic.main.activity_splash.etUsername
import kotlinx.android.synthetic.main.activity_splash.pbLoading
import minsk.androidacademy.githubclient.R
import minsk.androidacademy.githubclient.extensions.subscribe
import minsk.androidacademy.githubclient.feature.splash.di.splashActivityModule
import minsk.androidacademy.githubclient.feature.splash.di.splashBindingKodeinModule
import minsk.androidacademy.githubclient.feature.splash.presentation.SplashViewModel
import minsk.androidacademy.githubclient.mvp.getViewModel
import minsk.androidacademy.githubclient.navigation.Router
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SplashActivity : AppCompatActivity(), KodeinAware {

    override lateinit var kodein: Kodein

    private fun initKodein() {
        kodein = Kodein {
            extend((application as KodeinAware).kodein)
            import(splashActivityModule)
            import(splashBindingKodeinModule)
            bind<AppCompatActivity>() with provider { this@SplashActivity }
        }
    }

    private val viewModel: SplashViewModel by getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKodein()
        setContentView(R.layout.activity_splash)

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

        val router: Router by instance()
        router.setNavigator(direct.instance())
    }

    override fun onPause() {
        super.onPause()

        val router: Router by instance()
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