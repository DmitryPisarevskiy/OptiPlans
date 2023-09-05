package com.example.optiplans.entities

data class Capacity(var name: String, var tag: String){
    var regimes: MutableList<Column> = mutableListOf()
    var capacities: MutableList<Capacity> = mutableListOf()
    var minConstraints: MutableList<Float> = mutableListOf()
    var maxConstraints: MutableList<Float> = mutableListOf()
}
