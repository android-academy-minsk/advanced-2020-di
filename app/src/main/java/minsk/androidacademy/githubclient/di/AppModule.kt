import android.app.Application
import minsk.androidacademy.githubclient.BuildConfig
import minsk.androidacademy.githubclient.R
import minsk.androidacademy.githubclient.navigation.Router
import minsk.androidacademy.githubclient.navigation.RouterImpl
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.MILLISECONDS

val appKodeinModule = Kodein.Module("AppKodeinModule") {
    bind<Router>() with singleton { RouterImpl() }
    bind<OkHttpClient>() with singleton { provideOkHttpClient() }
    bind<Retrofit>() with singleton { provideRetrofit(instance(), instance()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val builder = Builder()
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(BODY)
        builder.addInterceptor(logging)
    }
    builder.connectTimeout(60 * 1000.toLong(), MILLISECONDS)
        .readTimeout(60 * 1000.toLong(), MILLISECONDS)
    return builder.build()
}

private fun provideRetrofit(
    application: Application,
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(application.getString(R.string.endpoint))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}