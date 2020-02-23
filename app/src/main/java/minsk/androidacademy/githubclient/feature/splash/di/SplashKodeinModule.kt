package minsk.androidacademy.githubclient.feature.splash.di

import androidx.lifecycle.ViewModelProvider
import minsk.androidacademy.githubclient.feature.splash.data.UserRepositoryImpl
import minsk.androidacademy.githubclient.feature.splash.data.mapper.UserMapper
import minsk.androidacademy.githubclient.feature.splash.data.remote.GithubUserEndpoint
import minsk.androidacademy.githubclient.feature.splash.domain.UserRepository
import minsk.androidacademy.githubclient.feature.splash.domain.interactor.GetUserLoginUseCase
import minsk.androidacademy.githubclient.feature.splash.presentation.SplashViewModelFactory
import minsk.androidacademy.githubclient.feature.splash.ui.SplashNavigator
import minsk.androidacademy.githubclient.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val splashBindingKodeinModule = Kodein.Module("SplashKodeinModule ") {
    bind<UserMapper>() with provider { UserMapper.Impl() }
    bind<UserRepository>() with provider { UserRepositoryImpl(instance(), instance()) }
    bind<GetUserLoginUseCase>() with provider { GetUserLoginUseCase.Impl(instance()) }
    bind<ViewModelProvider.Factory>() with singleton {
        SplashViewModelFactory(
            instance(),
            instance()
        )
    }
}

val splashActivityModule = Kodein.Module("SplashActivityModule") {
    bind<Navigator>() with singleton { SplashNavigator(instance()) }
    bind<GithubUserEndpoint>() with singleton { instance<Retrofit>().create(GithubUserEndpoint::class.java) }
}