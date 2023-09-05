package com.example.optiplans.entities

data class Stream(var name: String, var tag: String){
    var regimes: MutableList<Column> = mutableListOf()
    var price: MutableList<Float> = mutableListOf()
    var cost: MutableList<Float> = mutableListOf()
    var minConstrSales: MutableList<Float> = mutableListOf()
    var maxConstrSales: MutableList<Float> = mutableListOf()
    var minConstrPurchases: MutableList<Float> = mutableListOf()
    var maxConstrPurchases: MutableList<Float> = mutableListOf()
}