package minsk.androidacademy.githubclient.feature.repos.di.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import minsk.androidacademy.githubclient.di.scope.PerScreen
import minsk.androidacademy.githubclient.feature.repos.data.UserRepositoriesRepositoryImpl
import minsk.androidacademy.githubclient.feature.repos.data.remote.GithubUserRepositoriesEndpoint
import minsk.androidacademy.githubclient.feature.repos.data.remote.mapper.UserRepositoryMapper
import minsk.androidacademy.githubclient.feature.repos.domain.UserRepositoriesRepository
import minsk.androidacademy.githubclient.feature.repos.domain.interactor.GetUserRepositoriesUseCase
import minsk.androidacademy.githubclient.feature.repos.presentation.RepositoriesListViewModel
import minsk.androidacademy.githubclient.feature.repos.presentation.RepositoriesListViewModelImpl
import minsk.androidacademy.githubclient.feature.repos.presentation.mapper.RepositoryMapper
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoriesListViewHolderFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolderBigFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolderFeaturedFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolderNormalFactory
import minsk.androidacademy.githubclient.mvp.ViewModelFactory
import minsk.androidacademy.githubclient.mvp.ViewModelKey
import retrofit2.Retrofit

@Module(includes = [UserRepositoriesModule::class])
internal interface UserRepositoriesBindingModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(RepositoriesListViewModel::class)
    @Binds
    fun bindRepositoriesListViewModel(impl: RepositoriesListViewModelImpl): ViewModel

    @Binds
    fun bindGetUserRepositoriesUseCase(
        impl: GetUserRepositoriesUseCase.Impl
    ): GetUserRepositoriesUseCase

    @Binds
    fun bindUserRepositoriesRepository(
        impl: UserRepositoriesRepositoryImpl
    ): UserRepositoriesRepository

    @Binds
    fun bindUserRepositoryMapper(impl: UserRepositoryMapper.Impl): UserRepositoryMapper

    @Binds
    fun bindRepositoryMapper(impl: RepositoryMapper.Impl): RepositoryMapper

    @Binds
    @IntoMap
    @RepoTypeEnumKey(Type.TYPE_NORMAL)
    fun bindViewHolderNormal(impl: RepositoryViewHolderNormalFactory): RepositoriesListViewHolderFactory

    @Binds
    @IntoMap
    @RepoTypeEnumKey(Type.TYPE_BIG)
    fun bindViewHolderBig(impl: RepositoryViewHolderBigFactory): RepositoriesListViewHolderFactory

    @Binds
    @IntoMap
    @RepoTypeEnumKey(Type.TYPE_FEATURED)
    fun bindViewHolderFeatured(impl: RepositoryViewHolderFeaturedFactory): RepositoriesListViewHolderFactory
}

@Module
internal class UserRepositoriesModule {

    @Provides
    @PerScreen
    fun provideGithubUserRepositoriesEndpoint(retrofit: Retrofit): GithubUserRepositoriesEndpoint {
        return retrofit.create(GithubUserRepositoriesEndpoint::class.java)
    }

    //    @Provides
    //    @PerScreen
    //    fun provideRepositoriesListAdapter(
    //        viewHolderFactories: Map<Int, RepositoriesListViewHolderFactory>
    //    ): RepositoriesListAdapter {
    //        return RepositoriesListAdapter(viewHolderFactories)
    //    }

    @Provides
    @PerScreen
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    //    @Provides
    //    @IntoMap
    //    @RepoTypeEnumKey(TYPE.TYPE_NORMAL)
    //    fun provideViewHolderNormal(): RepositoriesListViewHolderFactory? {
    //        return RepositoryViewHolderNormalFactory()
    //    }
    //
    //    @Provides
    //    @IntoMap
    //    @RepoTypeEnumKey(TYPE.TYPE_BIG)
    //    fun provideViewHolderBig(): RepositoriesListViewHolderFactory? {
    //        return RepositoryViewHolderBigFactory()
    //    }
    //
    //    @Provides
    //    @IntoMap
    //    @RepoTypeEnumKey(TYPE.TYPE_FEATURED)
    //    fun provideViewHolderFeatured(): RepositoriesListViewHolderFactory {
    //        return RepositoryViewHolderFeaturedFactory()
    //    }
}

@MapKey
internal annotation class RepoTypeEnumKey(val value: Type)