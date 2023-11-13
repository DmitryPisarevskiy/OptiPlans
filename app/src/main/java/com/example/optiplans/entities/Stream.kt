package com.example.optiplans.entities

data class Stream(var name: String, var tag: String, var numOfPeriods: Int){
    var regimes: MutableList<Column> = mutableListOf()
    var price = Array<Float>(numOfPeriods){0f}
    var cost = Array<Float>(numOfPeriods){0f}
    var minConstrSales = Array<Float>(numOfPeriods){0f}
    var maxConstrSales = Array<Float>(numOfPeriods){0f}
    var minConstrPurchases = Array<Float>(numOfPeriods){0f}
    var maxConstrPurchases = Array<Float>(numOfPeriods){0f}
}