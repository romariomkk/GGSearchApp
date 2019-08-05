package com.romariomkk.ggsearch.di.module

import android.content.Context
import android.content.SharedPreferences
import com.romariomkk.ggsearch.core.domain.storage.PrefStorageImpl
import com.romariomkk.ggsearch.core.domain.storage.contract.SearchStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    internal fun provideSharePrefInstance(context: Context): SharedPreferences {
        return context.getSharedPreferences(PrefStorageImpl.STORAGE, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun provideSearchStorage(sharedPreferences: SharedPreferences): SearchStorage {
        return PrefStorageImpl(sharedPreferences)
    }
}
