package com.giorgi.jibladze.football.di

import com.giorgi.jibladze.football.ui.match.MatchModule
import com.giorgi.jibladze.football.ui.match.MatchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MatchModule::class])
    internal abstract fun matchesFragment(): MatchesFragment
}