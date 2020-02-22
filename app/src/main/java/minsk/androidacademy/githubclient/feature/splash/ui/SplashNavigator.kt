package minsk.androidacademy.githubclient.feature.splash.ui

import androidx.appcompat.app.AppCompatActivity
import minsk.androidacademy.githubclient.feature.repos.ui.RepositoriesListActivity
import minsk.androidacademy.githubclient.navigation.Destination
import minsk.androidacademy.githubclient.navigation.Navigator

class SplashNavigator(private val activity: AppCompatActivity) : Navigator {

    override fun navigateTo(destination: Destination) {
        when (destination) {
            is Destination.RepositoryList -> {
                activity.startActivity(
                    RepositoriesListActivity.newIntent(activity, destination.userLogin)
                )
            }
            Destination.Back -> {
                activity.onBackPressed()
            }
            else -> {
                throw IllegalArgumentException("cannot navigate to $destination")
            }
        }
    }
}