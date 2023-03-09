package com.cnexia.guidomia.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageSrc")
fun loadImage(view: ImageView, imageResource: Int) {
    view.setImageResource(imageResource)
}



