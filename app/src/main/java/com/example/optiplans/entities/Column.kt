package com.example.optiplans.entities

data class Column(var name: String, var tag: String, var unit: Unit?){
    var nameOfColumn: String = (unit?.tag ?: "") + tag
    var materialStreams: MutableList<Stream> = mutableListOf()
    var cost: MutableList<Float> = mutableListOf()

}

