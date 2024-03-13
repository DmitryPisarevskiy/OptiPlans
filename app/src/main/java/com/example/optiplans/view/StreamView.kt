package com.example.optiplans.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
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
        val path = Path().apply {
            setFillType(Path.FillType.EVEN_ODD)
            moveTo(width*0.1f,height*0.1f)
            lineTo(width*0.9f, height/2f)
            lineTo(width*0.1f, height*0.9f)
            lineTo(width*0.1f,height*0.1f)
            close()
        }
        canvas.drawPath(path, fillPaint)
        canvas.drawPath(path, strokePaint)
    }

    fun painting(fColor: Int) {
        fillPaint.color = fColor
        invalidate()
    }
}