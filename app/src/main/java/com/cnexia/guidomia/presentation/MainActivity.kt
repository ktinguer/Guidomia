package com.cnexia.guidomia.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.cnexia.guidomia.R
import com.cnexia.guidomia.databinding.ActivityMainBinding
import com.cnexia.guidomia.domain.model.Cars
import com.cnexia.guidomia.presentation.adapter.CarsAdapter
import com.cnexia.guidomia.presentation.adapter.CustomSpinnerAdapter
import com.cnexia.guidomia.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CarsViewModel by viewModels()
    private lateinit var carsAdapter: CarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observeViewModel()

        setupSpinnerListener(binding.spinnerMake) { _, item ->
            viewModel.makeSelected(item)
        }

        setupSpinnerListener(binding.spinnerModel) { _, item ->
            val selectedItem = binding.spinnerMake.selectedItem
            viewModel.modelSelected(item, selectedItem.toString())
        }
    }


    private fun observeViewModel() {

        observe(viewModel.makeList, ::handleMakesData)
        observe(viewModel.modelList, ::handleModelsData)
        observe(viewModel.cars, ::handleCarsData)
    }

    private fun handleMakesData(makes: List<String>) {
            binding.spinnerMake.adapter = CustomSpinnerAdapter(this, makes)
    }

    private fun handleModelsData(models: List<String>) {
            binding.spinnerModel.adapter = CustomSpinnerAdapter(this, models)
    }

    private fun handleCarsData(carsResource: Resource<Cars>) = when (carsResource) {
        is Resource.Loading -> binding.progressBar.toVisible()
        is Resource.Success -> carsResource.data.let {
            binding.progressBar.toGone()
            it?.let {
                val cars = it.carsList
                if (cars.isNotEmpty()) cars[0].expanded = true
                carsAdapter = CarsAdapter(cars)
                binding.carsRecyclerView.adapter = carsAdapter
                binding.carsRecyclerView.addDivider(this, ContextCompat.getDrawable(this, R.drawable.list_divider))
            }
        }
        is Resource.Error -> {
            binding.progressBar.toGone()
            Toast.makeText(this, carsResource.message?.asString(this), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_main) {
            Toast.makeText(this, getString(R.string.open_menu), Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}