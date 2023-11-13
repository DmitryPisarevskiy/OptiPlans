package com.example.optiplans.entities

data class Model(var numOfPeriods:Int) {
    var periods = Array<Byte>(numOfPeriods) {0}
    init {

    }
}
