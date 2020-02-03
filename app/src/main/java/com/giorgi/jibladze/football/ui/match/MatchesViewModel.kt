package com.giorgi.jibladze.football.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.giorgi.jibladze.football.Consts.WIFI_CONNECTION
import com.giorgi.jibladze.football.model.ViewMocky
import com.giorgi.jibladze.football.network.data.LoadMockyUseCase
import com.giorgi.jibladze.football.network.data.Result
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MatchesViewModel @Inject constructor(
    private val loadMockyUseCase: LoadMockyUseCase,
    @Named(WIFI_CONNECTION) isConnected: Boolean
) : ViewModel() {

    private val _mockyLiveData = MediatorLiveData<ViewMocky>()
    val mockyLiveData: LiveData<ViewMocky>
        get() = _mockyLiveData

    private val _loaderLiveData = MediatorLiveData<Boolean>()
    val loaderLiveData: LiveData<Boolean>
        get() = _loaderLiveData

    private val _loadeInternetErrorFragment = MediatorLiveData<Boolean>()
    val loadeInternetErrorFragment: LiveData<Boolean>
        get() = _loadeInternetErrorFragment

    init {

        if (isConnected) {
            _loadeInternetErrorFragment.postValue(false)
            loadData()
        } else {
            _loadeInternetErrorFragment.postValue(true)
        }
    }

    fun loadData() {
        _loaderLiveData.postValue(true)
        loadMockyUseCase.execute(Unit)
        val data = loadMockyUseCase.observe()

        _mockyLiveData.addSource(data) {
            _loaderLiveData.postValue(false)
            (it as? Result.Success)?.let { result ->
                _mockyLiveData.postValue(result.data)
            }
        }
    }
}