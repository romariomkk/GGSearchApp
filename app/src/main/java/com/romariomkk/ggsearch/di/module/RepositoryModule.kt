package com.romariomkk.ggsearch.di.module

import com.romariomkk.ggsearch.core.domain.repo.contract.SearchRepo
import com.romariomkk.ggsearch.core.domain.repo.SearchRepoImpl
import dagger.Binds
import dagger.Module

@Module(includes = [StorageModule::class])
interface RepositoryModule {

    @Binds
    fun bindSearchRepository(repository: SearchRepoImpl): SearchRepo
}