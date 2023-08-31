package com.example.optiplans.entities

data class Model(var numOfPeriods:Byte) {
    var periods: MutableList<Byte> = listOf<>()
    init {

    }
}
