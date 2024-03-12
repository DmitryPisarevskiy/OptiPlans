package com.example.optiplans.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class StreamView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
): View(context,attrs,defStyleAttr){

    private var asFeed: Boolean = true

    private val fillPaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        color = Color.DKGRAY
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(width*0.1f,-height*0.4f,width*1.9f,height*1.4f,155f,50f,true,fillPaint)
        canvas.drawArc(width*0.1f,-height*0.4f,width*1.9f,height*1.4f,155f,50f,true,strokePaint)
    }

    fun painting(fColor: Int) {
        fillPaint.color = fColor
        invalidate()
    }
}