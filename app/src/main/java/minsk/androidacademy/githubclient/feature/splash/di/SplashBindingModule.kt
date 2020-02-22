package minsk.androidacademy.githubclient.feature.splash.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import minsk.androidacademy.githubclient.di.scope.PerFeature
import minsk.androidacademy.githubclient.feature.splash.data.UserRepositoryImpl
import minsk.androidacademy.githubclient.feature.splash.data.mapper.UserMapper
import minsk.androidacademy.githubclient.feature.splash.data.remote.GithubUserEndpoint
import minsk.androidacademy.githubclient.feature.splash.domain.UserRepository
import minsk.androidacademy.githubclient.feature.splash.domain.interactor.GetUserLoginUseCase
import minsk.androidacademy.githubclient.feature.splash.presentation.SplashViewModel
import minsk.androidacademy.githubclient.feature.splash.presentation.SplashViewModelImpl
import minsk.androidacademy.githubclient.mvp.ViewModelFactory
import minsk.androidacademy.githubclient.mvp.ViewModelKey
import retrofit2.Retrofit

@Module(includes = [SplashModule::class])
internal abstract class SplashBindingModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    @Binds
    abstract fun bindSplashViewModel(impl: SplashViewModelImpl): ViewModel

    @Binds
    abstract fun bindGetUserLoginUseCase(impl: GetUserLoginUseCase.Impl): GetUserLoginUseCase

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindUserMapper(impl: UserMapper.Impl): UserMapper
}

@Module
internal class SplashModule {

    @Provides
    @PerFeature
    fun provideGithubApiService(retrofit: Retrofit): GithubUserEndpoint {
        return retrofit.create(GithubUserEndpoint::class.java)
    }
}