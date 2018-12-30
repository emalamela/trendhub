package com.chimichanga.trendhub.common.di.component

import android.app.Application
import com.chimichanga.trendhub.TrendhubApplication
import com.chimichanga.trendhub.common.di.module.NetworkingModule
import com.chimichanga.trendhub.common.di.module.ViewModelFactoryModule
import com.chimichanga.trendhub.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        ViewModelFactoryModule::class,
        NetworkingModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(trendhubApplication: TrendhubApplication)

}