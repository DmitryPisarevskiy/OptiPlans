package com.example.optiplans.entities

data class Unit(var name: String, var tag: String, val numOfPeriods: Int){
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
                        feeds.put(m.key,m.value*it.activities)
                    }
                }
            }
        }
        feeds = regimes
    }
}

