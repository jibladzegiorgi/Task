package com.giorgi.jibladze.football.ui.match

import androidx.lifecycle.ViewModel
import com.giorgi.jibladze.football.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MatchModule {

    @Binds
    @IntoMap
    @ViewModelKey(MatchesViewModel::class)
    abstract fun bindMatchesViewModel(viewModel: MatchesViewModel): ViewModel
}