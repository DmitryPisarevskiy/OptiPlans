package com.example.optiplans.entities

class UnitBalance (val unit: Unit) {
    var feeds: MutableList<Stream> = mutableListOf()
    var products: MutableList<Stream> = mutableListOf()
    var isEqual: Boolean = false
    init {

    }
}