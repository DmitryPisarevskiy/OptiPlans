package com.example.optiplans.entities

import android.view.View
import android.view.ViewGroup
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.setMargins

fun Array<Float>.times(value: Float): Array<Float> {
    val result = Array<Float> (this.size) {0f}
    for (i in 0..<this.size) {
        result[i] = this[i]*value
    }
    return result
}

fun Array<Float>.plus(array: Array<Float>): Array<Float> {
    val result = Array<Float> (this.size) {0f}
    for (i in 0..<this.size) {
        result[i] = this[i]+array[i]
    }
    return result
}

fun View.setMargins(
    left: Int = this.marginLeft,
    top: Int = this.marginTop,
    right: Int = this.marginRight,
    bottom: Int = this.marginBottom,
) {
    val params = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT,ViewGroup.MarginLayoutParams.WRAP_CONTENT)
    params.setMargins(0)
    layoutParams = params
}
