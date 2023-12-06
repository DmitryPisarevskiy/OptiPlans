package com.example.optiplans.entities

import com.example.optiplans.entities.Extensions

class Unit(var name: String, var tag: String, val numOfPeriods: Int){
    var regimes: MutableList<Column> = mutableListOf()
    var capacities: MutableList<Capacity> = mutableListOf()
    var isBalanced: Boolean = false
    var feeds: MutableMap<Stream, Array<Float>> = mutableMapOf()
    var products: MutableMap<Stream, Array<Float>> = mutableMapOf()
    var isEqual: Boolean = false
    var feedSum: Float=0f
    var productSum: Float=0f

    fun balance() {
        regimes.forEach {
            for (m in it.strAndCoeffs) {
                if (m.value>0) {
                    if (!feeds.containsKey(m.key)) {
                        feeds.put(m.key,it.activities.times(m.value))
                    } else {
                        feeds[m.key]?.plus(it.activities.times(m.value))
                    }
                } else if (m.value<0) {
                    if (!products.containsKey(m.key)) {
                        products.put(m.key,it.activities.times(m.value))
                    } else {
                        products[m.key]?.plus(it.activities.times(m.value))
                    }
                }
            }
        }
    }
    operator fun Array<Float>.times(value: Float): Array<Float> {
        val result = Array<Float> (this.size) {0f}
        for (i in 0..<this.size) {
            result[i] = this[i]*value
        }
        return result
    }

    operator fun Array<Float>.plus(array: Array<Float>): Array<Float> {
        val result = Array<Float> (this.size) {0f}
        for (i in 0..<this.size) {
            result[i] = this[i]+array[i]
        }
        return result
    }
}


