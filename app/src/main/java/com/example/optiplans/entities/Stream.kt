package com.example.optiplans.entities

data class Stream(
    var name: String,
    var tag: String,
    var numOfPeriods: Int,
    var sold: Boolean = false,
    var bought: Boolean = false
) {
    var prices = Array<Float>(numOfPeriods) { 0f }
    var costs = Array<Float>(numOfPeriods) { 0f }
    var minBoundsSales = Array<Float>(numOfPeriods) { 0f }
    var maxBoundsSales = Array<Float>(numOfPeriods) { 0f }
    var minBoundsPurchases = Array<Float>(numOfPeriods) { 0f }
    var maxBoundsPurchases = Array<Float>(numOfPeriods) { 0f }

}