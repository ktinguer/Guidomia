package com.cnexia.guidomia.presentation.adapter

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cnexia.guidomia.R
import com.cnexia.guidomia.databinding.ListItemCarBinding
import com.cnexia.guidomia.domain.model.CarUi
import com.cnexia.guidomia.util.inflater

class CarsViewHolder private constructor(private val binding: ListItemCarBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(carItem: CarUi) {
        binding.apply {
            car = carItem
            layoutProsList.removeAllViews()
            layoutConsList.removeAllViews()
            carItem.prosList.forEach {
                if (it.isNotEmpty()) layoutProsList.addView(getBulletText(it))
            }
            carItem.consList.forEach {
                if (it.isNotEmpty()) layoutConsList.addView(getBulletText(it))
            }
        }
    }

    private fun getBulletText(text: String): TextView {
        val textView = TextView(itemView.context)
        textView.text = text
        textView.setTextColor(itemView.context.getColor(R.color.black))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.bullet, 0, 0, 0)
        textView.compoundDrawablePadding = 20
        return textView
    }


    companion object {
        fun from(parent: ViewGroup): CarsViewHolder {
            val binding = ListItemCarBinding.inflate(parent.inflater, parent, false)
            return CarsViewHolder(binding)
        }
    }
}