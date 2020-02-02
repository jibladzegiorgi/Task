package com.giorgi.jibladze.football.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.giorgi.jibladze.football.model.ViewMocky
import com.giorgi.jibladze.football.network.data.LoadMockyUseCase
import com.giorgi.jibladze.football.network.data.Result
import javax.inject.Inject

class MatchesViewModel @Inject constructor(
    loadMockyUseCase: LoadMockyUseCase
) : ViewModel() {

    private val _mockyLiveData = MediatorLiveData<ViewMocky>()
    val mockyLiveData: LiveData<ViewMocky>
        get() = _mockyLiveData

    init {

        loadMockyUseCase.execute(Unit)
        val data = loadMockyUseCase.observe()

        _mockyLiveData.addSource(data) {
            (it as? Result.Success)?.let { result ->
                _mockyLiveData.postValue(result.data)
            }
        }
    }
}