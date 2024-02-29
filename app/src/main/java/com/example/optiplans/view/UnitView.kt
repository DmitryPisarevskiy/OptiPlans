package com.example.optiplans.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class UnitView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): View(context,attrs,defStyleAttr){

    private val fillPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Рисуем круг на Canvas
        canvas.drawCircle(width / 2f, height / 2f, 100f, fillPaint)
        // Рисуем прямоугольник на Canvas
        canvas.drawRect(50f, 50f, 200f, 200f, strokePaint)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
}