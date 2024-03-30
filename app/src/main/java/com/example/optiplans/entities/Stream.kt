package com.example.optiplans.entities

import android.graphics.Color
import kotlin.math.abs

class Stream(
    var name: String,
    var tag: String,
    var numOfPeriods: Int,
    var sold: Boolean = false,
    var bought: Boolean = false,
    var color: Int = Color.parseColor("#DADADA")
) {
    var prices = Array<Float>(numOfPeriods) { -1f }
    var costs = Array<Float>(numOfPeriods) { -1f }
    var sails = Array<Float> (numOfPeriods) {-1f}
    var purchases = Array<Float> (numOfPeriods) {-1f}
    var minBoundsSales = Array<Float>(numOfPeriods) { -1f }
    var maxBoundsSales = Array<Float>(numOfPeriods) { -1f }
    var minBoundsPurchases = Array<Float>(numOfPeriods) { -1f }
    var maxBoundsPurchases = Array<Float>(numOfPeriods) { -1f }
    var balance: MutableMap<Column, Float> = mutableMapOf()
    var isBalanced: Boolean = false
    var isEqualed: Boolean = false
    var producingUnits: MutableMap<Unit, Array<Float>> = mutableMapOf()
    var spendingUnits: MutableMap<Unit, Array<Float>> = mutableMapOf()
    var produced = Array<Float>(numOfPeriods) { 0f }
    var spent = Array<Float>(numOfPeriods) { 0f }

    fun materialBalance(model: Model) {
        produced = Array<Float>(numOfPeriods) { 0f }
        spent = Array<Float>(numOfPeriods) { 0f }
        balance = mutableMapOf()
        producingUnits = mutableMapOf()
        spendingUnits = mutableMapOf()
        model.units.forEach { u ->
            u.regimes.forEach { c ->
                c.strAndCoeffs[this]?.let { coeff ->
                    balance[c] = coeff
                    if (coeff > 0.0) {
                        for (i in 0..<numOfPeriods) {
                            spent[i] += c.activities[i] * coeff
                        }
                    } else {
                        for (i in 0..<numOfPeriods) {
                            produced[i] += c.activities[i] * coeff
                        }
                    }
                }
            }
        }
        isBalanced = true
        isEqualed = true
        for (i in 0..<numOfPeriods) {
            if (abs(produced[i] + spent[i] - purchases[i] + sails [i])> 0.001) {
                isEqualed = false
            }
        }
        balance.forEach {
            var values = it.key.activities.times(it.value)
            if (it.value>0) {
                if (spendingUnits.containsKey(it.key.parentUnit)) {
                    spendingUnits[it.key.parentUnit]=spendingUnits[it.key.parentUnit]!!.plus(values)
                } else {
                    spendingUnits[it.key.parentUnit] = values
                }
            } else if (it.value<0) {
                values = values.times(-1f)
                if (producingUnits.containsKey(it.key.parentUnit)) {
                    producingUnits[it.key.parentUnit]=producingUnits[it.key.parentUnit]!!.plus(values)
                } else {
                    producingUnits[it.key.parentUnit] = values
                }
            }
        }
    }
}