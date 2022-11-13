package com.agutierrezt.weatherexample.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agutierrezt.weatherexample.data.models.Forecast
import com.agutierrezt.weatherexample.data.repositories.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(val repo: WeatherRepository): ViewModel() {

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    private var _forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecast: LiveData<Forecast> get() = _forecast

    fun forecast(lat: Double, lng: Double) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val result = repo.forecast(lat, lng)
                _forecast.postValue(result)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun historical(lat: Double, lng: Double, startDate: String, endDate: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val result = repo.historical(lat, lng,startDate,endDate)
                _forecast.postValue(result)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }

}