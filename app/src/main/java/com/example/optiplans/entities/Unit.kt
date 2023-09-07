package com.example.optiplans.entities

data class Unit(var name: String, var tag: String){
    var regimes: MutableList<Column> = mutableListOf()
    var capacities: MutableList<Capacity> = mutableListOf()
    var unitBalance: UnitBalance() =
}

