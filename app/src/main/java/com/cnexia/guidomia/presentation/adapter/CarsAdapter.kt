package com.cnexia.guidomia.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cnexia.guidomia.domain.model.CarUi

class CarsAdapter(
    private val cars: List<CarUi>
) : RecyclerView.Adapter<CarsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        return CarsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val item = cars[position]
        holder.bind(item)
    }

    override fun getItemCount() = cars.size
}