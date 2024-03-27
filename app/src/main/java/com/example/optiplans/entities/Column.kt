package com.example.optiplans.entities

class Column(var name: String, var tag: String, val numOfPeriods: Int, val parentUnit: Unit){
    var strAndCoeffs: MutableMap <Stream, Float> = mutableMapOf()
    var activities = Array<Float>(numOfPeriods) {0f}
    var utAndCoeffs: MutableMap<Utility, Float> = mutableMapOf()

    init {
        parentUnit.regimes.add(this)
    }
}

