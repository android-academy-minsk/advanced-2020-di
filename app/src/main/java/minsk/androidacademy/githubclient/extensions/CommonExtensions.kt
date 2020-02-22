package minsk.androidacademy.githubclient.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

val <T> T.exhaustive: T
    get() = this

fun <T> AppCompatActivity.subscribe(output: LiveData<T>, reaction: (T) -> Unit) {
    output.subscribe(this, reaction)
}

inline fun <T> LiveData<T>.subscribe(
    lifecycleOwner: LifecycleOwner,
    crossinline reaction: (T) -> Unit
) = observe(lifecycleOwner, Observer { param -> reaction.invoke(param) })