package com.chimichanga.trendhub.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chimichanga.trendhub.common.di.annotation.ViewModelKey
import com.chimichanga.trendhub.common.di.factory.ViewModelFactory
import com.chimichanga.trendhub.repository.list.RepositoryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindsRepositoryViewModel(repositoryListViewModel: RepositoryListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}