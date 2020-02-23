package minsk.androidacademy.githubclient.feature.repos.di

import android.content.Context
import dagger.BindsInstance
import dagger.Subcomponent
import minsk.androidacademy.githubclient.di.scope.PerScreen
import minsk.androidacademy.githubclient.feature.repos.di.model.UserLoginIdentifier
import minsk.androidacademy.githubclient.feature.repos.ui.RepositoriesListActivity

@Subcomponent(
    modules = [
        UserRepositoriesBindingModule::class
    ]
)
@PerScreen
interface UserRepositoriesComponent {

    fun inject(activity: RepositoriesListActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
            @BindsInstance userLogin: UserLoginIdentifier
        ): UserRepositoriesComponent
    }
}