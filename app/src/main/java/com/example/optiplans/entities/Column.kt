package com.example.optiplans.entities

class Column(var name: String, var tag: String, var unit: Unit?, val numOfPeriods: Int){
    var nameOfColumn: String = (unit?.tag ?: "") + tag
    var strAndCoeffs: MutableMap <Stream, Float> = mutableMapOf()
    var activities = Array<Float>(numOfPeriods) {0f}
    var Utilities: MutableList<Utility> = mutableListOf()
}

