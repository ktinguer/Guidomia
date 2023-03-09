package com.cnexia.guidomia.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cnexia.guidomia.domain.model.CarUi
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

    private val _makeList: MutableLiveData<List<String>> = MutableLiveData()
    val makeList: LiveData<List<String>> get() = _makeList

    private val _modelList: MutableLiveData<List<String>> = MutableLiveData()
    val modelList: LiveData<List<String>> get() = _modelList

    private var makesModelsMap = mutableMapOf<String, List<String>>()
    private var filterCars = emptyList<CarUi>()

    init {
        _makeList.value = listOf(ANY_MAKE)
        _modelList.value = listOf(ANY_MODEL)
        getAllCars()
    }

    private fun getAllCars() {
        viewModelScope.launch {
            _cars.value = Resource.Loading()
            getAllCarsUseCase().let {
                _cars.value = it
                it.data?.carsList?.let {cars -> filterCars = cars }
                initFilter()
            }

        }
    }

    private fun initFilter() {
        val makesData = filterCars.groupBy { it.make }

        makesModelsMap[ANY_MAKE] = listOf(ANY_MODEL)

        makesData.forEach { entry ->
            val make = entry.key
            val models = entry.value.map { it.model }.distinct().toMutableList()
            models.add(0, ANY_MODEL)
            makesModelsMap[make] = models
        }
        _makeList.value = makesModelsMap.keys.toList()
        _modelList.value = listOf(ANY_MODEL)
    }

    fun makeSelected(make: String) {
        _modelList.value = makesModelsMap[make]
        _cars.value = when (make) {
            ANY_MAKE -> Resource.Success(Cars(filterCars))
            else -> Resource.Success(Cars(filterCars.filter { it.make == make }))
        }
    }

    fun modelSelected(model: String, make: String) {
        val makeCars = when (make) {
            ANY_MAKE -> filterCars
            else -> filterCars.filter { it.make == make }
        }

        _cars.value = when (model) {
            ANY_MODEL -> Resource.Success(Cars(makeCars))
            else -> Resource.Success(Cars(makeCars.filter { it.model == model }))
        }
    }

    companion object {
        const val ANY_MAKE = "Any make"
        const val ANY_MODEL = "Any model"
    }
}