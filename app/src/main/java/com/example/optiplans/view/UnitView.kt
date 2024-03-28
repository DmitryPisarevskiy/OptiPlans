package com.example.optiplans.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class UnitView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
): View(context,attrs,defStyleAttr){

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
        canvas.drawRoundRect(width*0.1f,height*0.1f,width*0.9f,height*0.9f,width*0.1f,width*0.1f,fillPaint)
        canvas.drawRoundRect(width*0.1f,height*0.1f,width*0.9f,height*0.9f,width*0.1f,width*0.1f,strokePaint)
    }

    fun setColor(fColor: Int) {
        fillPaint.color = fColor
        invalidate()
    }

}