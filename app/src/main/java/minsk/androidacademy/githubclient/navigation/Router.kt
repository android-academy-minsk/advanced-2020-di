package minsk.androidacademy.githubclient.navigation


interface Router {

    fun goto(destination: Destination)

    fun setNavigator(navigator: Navigator?)
}