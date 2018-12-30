package com.chimichanga.trendhub.main

import com.chimichanga.trendhub.repository.detail.RepositoryDetailModule
import com.chimichanga.trendhub.repository.list.RepositoryListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [RepositoryListModule::class, RepositoryDetailModule::class])
    abstract fun contributeMainActivity(): MainActivity

}