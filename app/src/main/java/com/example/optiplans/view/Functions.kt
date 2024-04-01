package com.example.optiplans.view

import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.optiplans.R
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.collapse
import com.example.optiplans.entities.expand

fun collapseItem(collapseButton: View, viewToCollapse: View, isCollapsed: Boolean) {
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


@RequiresApi(Build.VERSION_CODES.M)
fun addMainTableRow(row: TableRow, values: Array<Float>, style: Int) {
    values.forEach {
        val textView = TextView(row.context)
        textView.text = if (it >= 0) it.toString() else ""
        textView.gravity = Gravity.CENTER
        row.gravity = Gravity.CENTER
        textView.setTextAppearance(style)
        row.addView(textView)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun addMainTablePeriodRow(row: TableRow, style: Int) {
    for (i in ModelExample.periods.indices) {
        val tv = TextView(row.context)
        tv.text =
            "Период " + (i + 1).toString() + System.lineSeparator() + ModelExample.periods[i].toString() + " дней"
        tv.gravity = Gravity.CENTER
        tv.setTextAppearance(style)
        row.addView(tv)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun addTableRow(row: TableRow, values: Array<Float>) {
    addMainTableRow(row, values, R.style.TableText)
}


@RequiresApi(Build.VERSION_CODES.M)
fun addTablePeriodRow(row: TableRow) {
    addMainTablePeriodRow(row, R.style.TableText)
}

@RequiresApi(Build.VERSION_CODES.M)
fun addItemTableRow(row: TableRow, values: Array<Float>) {
    addMainTableRow(row, values, R.style.ItemText)
}


@RequiresApi(Build.VERSION_CODES.M)
fun addItemTablePeriodRow(row: TableRow) {
    addMainTablePeriodRow(row, R.style.ItemText)
}