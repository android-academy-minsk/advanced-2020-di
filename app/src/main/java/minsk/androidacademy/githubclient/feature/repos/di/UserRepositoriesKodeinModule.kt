package minsk.androidacademy.githubclient.feature.repos.di

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import minsk.androidacademy.githubclient.feature.repos.data.UserRepositoriesRepositoryImpl
import minsk.androidacademy.githubclient.feature.repos.data.remote.GithubUserRepositoriesEndpoint
import minsk.androidacademy.githubclient.feature.repos.data.remote.mapper.UserRepositoryMapper
import minsk.androidacademy.githubclient.feature.repos.domain.UserRepositoriesRepository
import minsk.androidacademy.githubclient.feature.repos.domain.interactor.GetUserRepositoriesUseCase
import minsk.androidacademy.githubclient.feature.repos.presentation.RepositoriesListViewModelFactory
import minsk.androidacademy.githubclient.feature.repos.presentation.mapper.RepositoryMapper
import minsk.androidacademy.githubclient.feature.repos.presentation.model.Type
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.RepositoriesListAdapter
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoriesListViewHolderFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolderBigFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolderFeaturedFactory
import minsk.androidacademy.githubclient.feature.repos.ui.adapter.viewholder.RepositoryViewHolderNormalFactory
import minsk.androidacademy.githubclient.feature.splash.ui.SplashNavigator
import minsk.androidacademy.githubclient.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.multiton
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val userRepositoriesBindingKodeinModule = Kodein.Module("UserRepositoriesActivityBindingModule ") {
    bind<GetUserRepositoriesUseCase>() with provider { GetUserRepositoriesUseCase.Impl(instance()) }
    bind<UserRepositoryMapper>() with provider { UserRepositoryMapper.Impl() }
    bind<RepositoryMapper>() with provider { RepositoryMapper.Impl() }
    bind<UserRepositoriesRepository>() with provider {
        UserRepositoriesRepositoryImpl(
            instance(),
            instance()
        )
    }
    bind<ViewModelProvider.Factory>() with singleton {
        RepositoriesListViewModelFactory(
            instance(),
            instance(),
            instance()
        )
    }
}

val userRepositoriesModule = Kodein.Module("UserRepositoriesActivityModule") {
    bind<Navigator>() with singleton { SplashNavigator(instance()) }
    bind<LinearLayoutManager>() with singleton { LinearLayoutManager(instance()) }
    bind<RepositoriesListAdapter>() with singleton { RepositoriesListAdapter(kodein.direct) }
    bind<GithubUserRepositoriesEndpoint>() with singleton {
        instance<Retrofit>().create(GithubUserRepositoriesEndpoint::class.java)
    }
    bind<RepositoriesListViewHolderFactory>() with factory { type: Type ->
        when (type) {
            Type.TYPE_FEATURED -> RepositoryViewHolderFeaturedFactory()
            Type.TYPE_BIG -> RepositoryViewHolderBigFactory()
            Type.TYPE_NORMAL -> RepositoryViewHolderNormalFactory()
        }
    }
}