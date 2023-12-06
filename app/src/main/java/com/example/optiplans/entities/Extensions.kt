package com.example.optiplans.entities

class Extensions {
    public operator fun Array<Float>.times(value: Float): Array<Float> {
        val result = Array<Float> (this.size) {0f}
        for (i in 0..<this.size) {
            result[i] = this[i]*2
        }
        return result
    }
}