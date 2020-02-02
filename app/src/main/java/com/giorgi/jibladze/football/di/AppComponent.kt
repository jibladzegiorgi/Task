package com.giorgi.jibladze.football.di

import com.giorgi.jibladze.football.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        DataSourceModule::class,
        FragmentBindingModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApp): AppComponent
    }
}