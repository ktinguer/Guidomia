package com.cnexia.guidomia.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cnexia.guidomia.databinding.ListItemCarBinding
import com.cnexia.guidomia.domain.model.CarUi
import com.cnexia.guidomia.util.inflater

class CarsViewHolder private constructor(private val binding: ListItemCarBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(carItem: CarUi) {
        binding.car = carItem
    }

    companion object {
        fun from(parent: ViewGroup): CarsViewHolder {
            val binding = ListItemCarBinding.inflate(parent.inflater, parent, false)
            return CarsViewHolder(binding)
        }
    }
}