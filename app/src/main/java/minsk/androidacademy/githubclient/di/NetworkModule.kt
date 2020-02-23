package minsk.androidacademy.githubclient.di

import android.app.Application
import dagger.Module
import dagger.Provides
import minsk.androidacademy.githubclient.BuildConfig
import minsk.androidacademy.githubclient.R.string
import minsk.androidacademy.githubclient.di.scope.AppScope
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.MILLISECONDS

@Module
internal class NetworkModule {

    @Provides
    @AppScope
    fun provideOkHttpClient(): OkHttpClient {
        val builder = Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logging)
        }
        builder.connectTimeout(60 * 1000.toLong(), MILLISECONDS)
            .readTimeout(60 * 1000.toLong(), MILLISECONDS)
        return builder.build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(
        application: Application,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(application.getString(string.endpoint))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}