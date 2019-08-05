package com.romariomkk.ggsearch.di

import android.app.Application
import com.romariomkk.ggsearch.GGSearchApp
import com.romariomkk.ggsearch.di.module.ActivityModule
import com.romariomkk.ggsearch.di.module.AppModule
import com.romariomkk.ggsearch.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ActivityModule::class,
            ViewModelModule::class,
            AppModule::class
        ])
interface AppComponent : AndroidInjector<GGSearchApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(satisfyerApp: GGSearchApp)
}