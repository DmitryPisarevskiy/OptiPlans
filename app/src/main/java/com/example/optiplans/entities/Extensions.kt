package com.example.optiplans.entities

import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation

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

fun collapse(v: View) {
    val initialHeight = v.measuredHeight
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if (interpolatedTime == 1f) {
                v.visibility = View.GONE
            } else {
                v.layoutParams.height =
                    initialHeight - (initialHeight * interpolatedTime).toInt()
                v.requestLayout()
            }
        }
        override fun willChangeBounds(): Boolean {
            return true
        }
    }
    a.duration = 500
    v.startAnimation(a)
}

fun expand(v: View) {
    val matchParentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
    val wrapContentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
    val targetHeight = v.measuredHeight

    v.layoutParams.height = 1
    v.visibility = View.VISIBLE
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            v.layoutParams.height =
                if (interpolatedTime == 1f) WindowManager.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
            v.requestLayout()
        }
        override fun willChangeBounds(): Boolean {
            return true
        }
    }
    a.duration = 500
    v.startAnimation(a)
}
