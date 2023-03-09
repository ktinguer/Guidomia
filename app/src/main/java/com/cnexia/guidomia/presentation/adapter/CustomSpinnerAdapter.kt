package com.cnexia.guidomia.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.cnexia.guidomia.databinding.SpinnerItemBinding
import com.cnexia.guidomia.databinding.SpinnerItemDropdownBinding
import com.cnexia.guidomia.util.inflater

class CustomSpinnerAdapter(
    context: Context,
    private val items: List<String>
) : ArrayAdapter<String>(context, 0, items) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SpinnerItemBinding.inflate(parent.inflater, parent, false)
        val tvSpinnerValue = binding.tvSpinnerValue
        tvSpinnerValue.text = items[position]
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SpinnerItemDropdownBinding.inflate(parent.inflater, parent, false)
        val tvSpinner = binding.tvSpinner
        tvSpinner.text = items[position]
        return binding.root
    }
}