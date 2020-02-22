package minsk.androidacademy.githubclient.feature.splash.di

import dagger.Component
import minsk.androidacademy.githubclient.di.AppComponentApi
import minsk.androidacademy.githubclient.di.scope.PerFeature
import minsk.androidacademy.githubclient.feature.splash.ui.SplashActivity
import minsk.androidacademy.githubclient.navigation.Router
import retrofit2.Retrofit

@Component(
    modules = [SplashBindingModule::class],
    dependencies = [FeatureSplashDependencies::class]
)
@PerFeature
interface FeatureSplashComponent {

    fun inject(activity: SplashActivity)

    object Factory {

        fun get(appComponentApi: AppComponentApi): FeatureSplashComponent {
            return DaggerFeatureSplashComponent.builder()
                .featureSplashDependencies(
                    DaggerFeatureSplashDependencies.builder()
                        .appComponentApi(appComponentApi)
                        .build()
                )
                .build()
        }
    }
}

@Component(
    dependencies = [AppComponentApi::class]
)
internal interface FeatureSplashDependencies {

    fun retrofit(): Retrofit
    fun router(): Router
}