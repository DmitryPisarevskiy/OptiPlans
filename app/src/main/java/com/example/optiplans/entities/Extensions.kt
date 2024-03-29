package com.example.optiplans.entities

import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
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

fun collapseItem(collapseButton: View, viewToCollapse:View, isCollapsed: Boolean) {
    var startAngle=0f
    var endAngle=180f
    if (isCollapsed) {
        startAngle = 180f
        endAngle = 0f
        expand(viewToCollapse)
    } else {
        collapse(viewToCollapse)
    }
    val animation = RotateAnimation(startAngle,endAngle,collapseButton.measuredWidth*0.5f,collapseButton.measuredHeight*0.5f)
    animation.duration = 100
    animation.fillAfter = true
    collapseButton.startAnimation(animation)
}