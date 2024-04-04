package com.example.optiplans.view

import com.example.optiplans.entities.QuantityMeasure

interface IMeasureListener {
    fun onMeasureSpinnerChange(measure: QuantityMeasure)
}