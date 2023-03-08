package com.cnexia.guidomia.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cnexia.guidomia.domain.model.Cars
import com.cnexia.guidomia.domain.usecase.GetAllCarsUseCase
import com.cnexia.guidomia.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getAllCarsUseCase: GetAllCarsUseCase
) : ViewModel() {

    private val _cars: MutableLiveData<Resource<Cars>> = MutableLiveData()
    val cars: LiveData<Resource<Cars>> get() = _cars

    init {
        getAllCars()
    }

    private fun getAllCars() {
        viewModelScope.launch {
            _cars.value = Resource.Loading()
            _cars.value = getAllCarsUseCase()
        }
    }
}