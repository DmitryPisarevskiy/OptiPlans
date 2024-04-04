package com.example.optiplans.entities
open class Model(var name:String, var numOfPeriods:Int) {
    var periods = Array<Byte>(numOfPeriods) {0}
    var units: MutableList<Unit> = mutableListOf()
    var streams: MutableList<Stream> = mutableListOf()
    var quantityMeasure = QuantityMeasure.PER_TIME_UNIT
    var quantityCoeff = Array<Int>(numOfPeriods) {0}
    var timeUnit: String = ""
    var quantityUnit: String = ""
    var measure = QuantityMeasure.PER_TIME_UNIT
}
