package minsk.androidacademy.githubclient.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import minsk.androidacademy.githubclient.di.scope.AppScope
import minsk.androidacademy.githubclient.navigation.Router
import retrofit2.Retrofit

@AppScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AppComponentApi {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}

interface AppComponentApi {

    fun retrofit(): Retrofit
    fun router(): Router
}