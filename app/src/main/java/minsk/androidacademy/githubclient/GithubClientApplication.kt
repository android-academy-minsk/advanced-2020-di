package minsk.androidacademy.githubclient

import android.app.Application
import android.content.Context
import minsk.androidacademy.githubclient.di.AppComponent
import minsk.androidacademy.githubclient.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class GithubClientApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        operator fun get(context: Context): GithubClientApplication {
            return context.applicationContext as GithubClientApplication
        }
    }
}