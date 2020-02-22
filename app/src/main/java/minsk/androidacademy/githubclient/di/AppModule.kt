package minsk.androidacademy.githubclient.di

import dagger.Binds
import dagger.Module
import minsk.androidacademy.githubclient.di.scope.AppScope
import minsk.androidacademy.githubclient.navigation.Router
import minsk.androidacademy.githubclient.navigation.RouterImpl

@Module
internal abstract class AppModule {

    @AppScope
    @Binds
    abstract fun bindRouter(impl: RouterImpl): Router
}