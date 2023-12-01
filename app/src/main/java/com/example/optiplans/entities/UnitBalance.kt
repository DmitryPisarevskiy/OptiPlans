package com.example.optiplans.entities

class UnitBalance (val unit: Unit) {
    var feeds: MutableMap<Stream, Float> = mutableMapOf()
    var products: MutableList<Stream> = mutableListOf()
    var isEqual: Boolean = false
    var feedSum: Float=0f
    var productSum: Float=0f

    init {
        unit.regimes.forEach {
            for (m in it.strAndCoeffs) {
                if (m.value>0) {
                    if (!feeds.containsKey(m.key)) {
                        feeds.put(m.key,m.value*it.activities)
                    }
                }
            }
        }
        feeds = unit.regimes
    }
}