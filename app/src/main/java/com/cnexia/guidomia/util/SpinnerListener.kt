package com.cnexia.guidomia.util

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

fun setupSpinnerListener(spinner: Spinner, onItemSelectedAction: (position: Int, item: String) -> Unit) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val selectedItem = parent?.getItemAtPosition(position).toString()
            onItemSelectedAction(position, selectedItem)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            // Do nothing
        }
    }
}