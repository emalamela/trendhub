package com.chimichanga.trendhub.repository.detail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RepositoryDetailModule {

    @ContributesAndroidInjector
    abstract fun contributeRepositoryDetailFragment(): RepositoryDetailFragment

}