package minsk.androidacademy.githubclient.feature.repos.di

import dagger.Component
import minsk.androidacademy.githubclient.di.AppComponentApi
import minsk.androidacademy.githubclient.di.scope.PerFeature
import minsk.androidacademy.githubclient.navigation.Router
import retrofit2.Retrofit

@Component(
    dependencies = [FeatureUserRepositoriesDependencies::class]
)
@PerFeature
interface FeatureUserRepositoriesComponent {

    object Factory {

        private var cachedComponent: FeatureUserRepositoriesComponent? = null

        fun get(coreBaseApi: AppComponentApi): FeatureUserRepositoriesComponent {
            return cachedComponent
                ?: createComponent(
                    coreBaseApi
                ).also { cachedComponent = it }
        }

        private fun createComponent(appComponentApi: AppComponentApi): FeatureUserRepositoriesComponent {
            return DaggerFeatureUserRepositoriesComponent.builder()
                .featureUserRepositoriesDependencies(
                    dependencies(
                        appComponentApi
                    )
                )
                .build()
        }

        private fun dependencies(appComponentApi: AppComponentApi): FeatureUserRepositoriesDependencies {
            return DaggerFeatureUserRepositoriesDependencies.builder()
                .appComponentApi(appComponentApi)
                .build()
        }
    }

    fun userRepositoriesComponent(): UserRepositoriesComponent.Factory
}

@Component(
    dependencies = [AppComponentApi::class]
)
internal interface FeatureUserRepositoriesDependencies {

    fun retrofit(): Retrofit
    fun router(): Router
}