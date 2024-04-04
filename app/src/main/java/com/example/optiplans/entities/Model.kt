package com.example.optiplans.entities
open class Model(var name:String, val numOfPeriods:Int, var timeUnit: String, var quantityUnit: String) {
    var periods = Array<Byte>(numOfPeriods) {0}
    var units: MutableList<Unit> = mutableListOf()
    var streams: MutableList<Stream> = mutableListOf()
    var measure: QuantityMeasure = QuantityMeasure.PER_TIME_UNIT
}
