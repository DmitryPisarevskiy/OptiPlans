package com.example.optiplans.entities
open class Model(var numOfPeriods:Int) {
    public var periods = Array<Byte>(numOfPeriods) {0}
    var units: MutableList<Unit> = mutableListOf()
    var streams: MutableList<Stream> = mutableListOf()
}
