package com.romariomkk.ggsearch.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.romariomkk.ggsearch.GGSearchApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class])
internal class AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApp(application: Application): GGSearchApp = application as GGSearchApp

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}
