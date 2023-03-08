package com.cnexia.guidomia.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

val ViewGroup.inflater: LayoutInflater get() = LayoutInflater.from(context)

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}