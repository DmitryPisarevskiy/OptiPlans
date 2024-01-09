package com.example.optiplans.entities

fun Array<Float>.times(value: Float): Array<Float> {
    val result = Array<Float> (this.size) {0f}
    for (i in 0..<this.size) {
        result[i] = this[i]*value
    }
    return result
}

fun Array<Float>.plus(array: Array<Float>): Array<Float> {
    val result = Array<Float> (this.size) {0f}
    for (i in 0..<this.size) {
        result[i] = this[i]+array[i]
    }
    return result
}