package minsk.androidacademy.githubclient.mvp

abstract class Presenter<T : View> {

    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
        onViewAttach()
    }

    protected open fun onViewAttach() = Unit

    fun detachView() {
        onViewDetach()
        view = null
    }

    protected open fun onViewDetach() = Unit
}