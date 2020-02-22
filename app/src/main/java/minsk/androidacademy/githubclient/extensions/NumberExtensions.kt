package minsk.androidacademy.githubclient.extensions

inline fun Long?.orZero(): Long = this ?: 0L

inline fun Int?.orZero(): Int = this ?: 0