package minsk.androidacademy.githubclient.navigation

class RouterImpl constructor() : Router {

    private var navigator: Navigator? = null

    private var nextDestination: Destination? = null

    override fun goto(destination: Destination) {
        navigator?.navigateTo(destination) ?: run { nextDestination = destination }
    }

    override fun setNavigator(navigator: Navigator?) {
        this.navigator = navigator
        nextDestination?.let { destination ->
            nextDestination = null
            navigator?.navigateTo(destination)
        }
    }
}