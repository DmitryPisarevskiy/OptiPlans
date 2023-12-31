package com.example.optiplans.entities

class Unit(var name: String, var tag: String, val numOfPeriods: Int){
    var regimes: MutableList<Column> = mutableListOf()
    var capacities: MutableList<Capacity> = mutableListOf()
    var isBalanced: Boolean = false
    var feeds: MutableMap<Stream, Array<Float>> = mutableMapOf()
    var products: MutableMap<Stream, Array<Float>> = mutableMapOf()
    var isEqualAll: Boolean = false
    var feedSum: Array<Float> = Array<Float> (numOfPeriods) {0f}
    var productSum: Array<Float> = Array<Float> (numOfPeriods) {0f}

    fun balance() {
        regimes.forEach {
            for (m in it.strAndCoeffs) {
                if (m.value>0) {
                    if (!feeds.containsKey(m.key)) {
                        feeds[m.key] = it.activities.times(m.value)
                    } else {
                        feeds[m.key]?.plus(it.activities.times(m.value))
                    }
                } else if (m.value<0) {
                    if (!products.containsKey(m.key)) {
                        products[m.key] = it.activities.times(m.value)
                    } else {
                        products[m.key]?.plus(it.activities.times(m.value))
                    }
                }
            }
        }
        feedSum = Array<Float> (numOfPeriods) {0f}
        feeds.forEach {
            feedSum.plus(it.value)
        }
        productSum = Array<Float> (numOfPeriods) {0f}
        products.forEach {
            productSum.plus(it.value)
        }
    }
}


