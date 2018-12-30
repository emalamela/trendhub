package com.chimichanga.trendhub.repository.list

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RepositoryListModule {

    @ContributesAndroidInjector
    abstract fun contributesRepositoryListFragment(): RepositoryListFragment

}