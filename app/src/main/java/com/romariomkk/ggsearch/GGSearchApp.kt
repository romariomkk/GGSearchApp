package com.romariomkk.ggsearch

import android.content.Context
import com.facebook.stetho.Stetho
import com.romariomkk.ggsearch.di.AppComponent
import com.romariomkk.ggsearch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GGSearchApp: DaggerApplication() {

    private lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        INSTANCE = this
        androidInjector = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        private var INSTANCE: GGSearchApp? = null
        @JvmStatic
        fun get(): GGSearchApp = INSTANCE!!

        @JvmStatic
        public fun getAppComponent(): AppComponent {
            return GGSearchApp.get().androidInjector as AppComponent
        }
    }

    public override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector
}