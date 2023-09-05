package com.example.optiplans.entities

data class Column(var name: String, var tag: String, var unit: Unit?){
    var nameOfColumn: String = (unit?.tag ?: "") + tag
    var regimes: MutableList<Column> = mutableListOf()
    var price: MutableList<Float> = mutableListOf()
    var cost: MutableList<Float> = mutableListOf()
    var minConstrSales: MutableList<Float> = mutableListOf()
    var maxConstrSales: MutableList<Float> = mutableListOf()
    var minConstrPurchases: MutableList<Float> = mutableListOf()
    var maxConstrPurchases: MutableList<Float> = mutableListOf()
}

