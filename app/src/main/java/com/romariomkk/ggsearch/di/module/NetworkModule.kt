package com.romariomkk.ggsearch.di.module

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.romariomkk.ggsearch.BuildConfig
import com.romariomkk.ggsearch.core.domain.api.GGSearchApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        stethoInterceptor: StethoInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
        with(builder) {

            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(stethoInterceptor)
                addNetworkInterceptor(httpLoggingInterceptor)
            }

            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message) })
            .apply { level = HttpLoggingInterceptor.Level.BODY }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): GGSearchApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.GOOGLE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GGSearchApi::class.java)

    @Provides
    @Singleton
    fun provideStethoInterceptor(): StethoInterceptor = StethoInterceptor()
}