package com.example.optiplans.logic

import com.example.optiplans.entities.UnitBalance

class UnitLogic {
    fun getUnitBalance(val u: Unit): UnitBalance() {
        UnitBalance(unit = u)
    }
}