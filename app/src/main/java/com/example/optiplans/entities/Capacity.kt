package com.example.optiplans.entities

data class Capacity(var name: String, var tag: String, val numOfPeriods: Int){
    var regimes: MutableList<Column> = mutableListOf()
    var activities = Array<Float>(numOfPeriods) {0f}
    var minConstraints = Array<Float>(numOfPeriods) {0f}
    var maxConstraints = Array<Float>(numOfPeriods) {0f}
}
