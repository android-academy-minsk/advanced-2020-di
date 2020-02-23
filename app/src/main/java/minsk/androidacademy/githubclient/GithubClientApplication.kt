package minsk.androidacademy.githubclient

import android.app.Application
import android.content.Context
import appKodeinModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class GithubClientApplication : Application(), KodeinAware {
    override val kodein = Kodein {
        import(appKodeinModule)
        bind<GithubClientApplication>() with provider { this@GithubClientApplication }
    }

    companion object {
        operator fun get(context: Context): GithubClientApplication {
            return context.applicationContext as GithubClientApplication
        }
    }
}
