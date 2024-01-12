package com.example.optiplans.entities

class Capacity(var name: String, var tag: String, val numOfPeriods: Int){
    var regimesAndCoeffs: MutableMap<Column, Float> = mutableMapOf()
    var activities = Array<Float>(numOfPeriods) {0f}
    var minBounds = Array<Float>(numOfPeriods) {0f}
    var maxBounds = Array<Float>(numOfPeriods) {0f}

    fun calculateActivities() {
        for (i in 0..numOfPeriods-1) {
            activities[i] = 0f
        }
        regimesAndCoeffs.forEach {
            for (i in 0..numOfPeriods - 1) {
                activities[i] += it.key.activities[i] * it.value
            }
        }
    }
}
