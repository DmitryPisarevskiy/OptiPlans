package com.example.optiplans.entities

import android.graphics.Color

object ModelExample : Model("ГПНС. Февраль" ,3, "д", "т") {
    var currentStream: Stream? = null
    var currentUnit: Unit? = null
    var currentPeriodNum = 0
    init {
        // Periods initialization
        this.periods[0] = 10
        this.periods[1] = 10
        this.periods[2] = 11

        // Unit creation
        val epUnit = Unit("ЭП-300", "MEP", 3, Color.parseColor("#CCB20B"))
        val hdpeUnit = Unit("ПЭНД", "PND", 3)
        val iaaUnit = Unit("ЛАК", "LAK", 3)

        // Stream construction
        val ethaneStream = Stream("Этан", "ETN", 3, bought = true, color = Color.parseColor("#AA4F4F"))
        this.streams.add(ethaneStream)
        val lpgStream = Stream("ШФЛУ", "SLU", 3, bought = true, color = Color.parseColor("#51DD06"))
        this.streams.add(lpgStream)
        val naphtaStream = Stream("Нафта", "NAF", 3, bought = true, color = Color.parseColor("#CC0BAB"))
        this.streams.add(naphtaStream)
        val ethyleneStream = Stream("Этилен", "ETY", 3, sold = true, color = Color.parseColor("#FFFFFFFF"))
        this.streams.add(ethyleneStream)
        val propyleneStream = Stream("Пропилен", "PRP", 3, color = Color.parseColor("#B8B8B8"))
        this.streams.add(propyleneStream)
        val tspStream = Stream("Пироконденсат", "PIR", 3, sold = true, color = Color.parseColor("#606060"))
        this.streams.add(tspStream)
        val hdpeStream = Stream("ПЭНД", "PND", 3, sold = true, color = Color.parseColor("#BC7E7E"))
        this.streams.add(hdpeStream)
        val iaaStream = Stream("ЛАК", "LAK", 3, sold = true, color = Color.parseColor("#1B263E"))
        this.streams.add(iaaStream)
        val waterStream = Stream("Вода", "WAT", 3, bought = true, color = Color.parseColor("#D8D375"))
        this.streams.add(waterStream)

        // Prices, costs
        ethaneStream.costs = arrayOf(20f,20f,20f)
        ethaneStream.minBoundsPurchases = arrayOf(1f,0f,0.5f)
        lpgStream.costs = arrayOf(10f,10f,10f)
        lpgStream.maxBoundsPurchases = arrayOf(10f,10f,10f)
        naphtaStream.costs = arrayOf(20f,20f,20f)
        ethyleneStream.prices = arrayOf(30f,30f,30f)
        tspStream.prices = arrayOf(10f,10f,10f)
        tspStream.maxBoundsSales = arrayOf(10f,10f,10f)
        hdpeStream.prices = arrayOf(40f,40f,40f)
        iaaStream.prices = arrayOf(10f,10f,10f)
        iaaStream.minBoundsSales = arrayOf(0f,0f,0f)
        waterStream.costs = arrayOf(10f,10f,10f)

        // Column construction
        val etnColumn = Column("Переработка этана", "ETN", 3, epUnit)
        val sluColumn = Column("Переработка ШФЛУ", "SLU", 3, epUnit)
        val nafColumn = Column("Переработка нафты", "NAF", 3, epUnit)
        val etyColumn = Column("Переработка этилена", "ETY", 3, hdpeUnit)
        val prlColumn = Column("Переработка пропилена", "PRL", 3, iaaUnit)

        // Column coefficients
        etnColumn.strAndCoeffs[ethaneStream] = 1f
        etnColumn.strAndCoeffs[ethyleneStream] = -0.8f
        etnColumn.strAndCoeffs[propyleneStream] = -0.1f
        etnColumn.strAndCoeffs[tspStream] = -0.1f
        sluColumn.strAndCoeffs[lpgStream] = 1f
        sluColumn.strAndCoeffs[ethyleneStream] = -0.7f
        sluColumn.strAndCoeffs[propyleneStream] = -0.15f
        sluColumn.strAndCoeffs[tspStream] = -0.15f
        nafColumn.strAndCoeffs[naphtaStream] = 1f
        nafColumn.strAndCoeffs[ethyleneStream] = -0.6f
        nafColumn.strAndCoeffs[propyleneStream] = -0.2f
        nafColumn.strAndCoeffs[tspStream] = -0.2f
        etyColumn.strAndCoeffs[ethyleneStream] = 1.0f
        etyColumn.strAndCoeffs[hdpeStream] = -1.0f
        prlColumn.strAndCoeffs[propyleneStream] = 1.0f
        prlColumn.strAndCoeffs[waterStream] = 0.85f
        prlColumn.strAndCoeffs[iaaStream] = -1.85f

        // Capacities creation
        val epEtyCap = Capacity("ЭП-355 по этилену", "ETY", 3)
        val epTotCap = Capacity("ЭП-355 по сырью", "EPT",3)
        val hdpeCap = Capacity("ПЭНД", "PND",3)
        val iaaCap = Capacity("ЛАК", "LAK",3)
        epEtyCap.regimesAndCoeffs[etnColumn] = 0.8f
        epEtyCap.regimesAndCoeffs[sluColumn] = 0.7f
        epEtyCap.regimesAndCoeffs[nafColumn] = 0.6f
        epTotCap.regimesAndCoeffs[etnColumn] = 1.0f
        epTotCap.regimesAndCoeffs[sluColumn] = 1.0f
        epTotCap.regimesAndCoeffs[nafColumn] = 1.0f
        hdpeCap.regimesAndCoeffs[etyColumn] = 1.0f
        iaaCap.regimesAndCoeffs[prlColumn] = 1.85f

        // Adding capacities to units
        epUnit.capacities.add(epEtyCap)
        epUnit.capacities.add(epTotCap)
        hdpeUnit.capacities.add(hdpeCap)
        iaaUnit.capacities.add(iaaCap)

        //Adding units to model
        this.units.add(epUnit)
        this.units.add(hdpeUnit)
        this.units.add(iaaUnit)

        //Activities settings
        etnColumn.activities = arrayOf(1f, 0f, 0f)
        sluColumn.activities = arrayOf(1f, 1f, 0f)
        nafColumn.activities = arrayOf(1f, 0f, 1f)
        etyColumn.activities = arrayOf(2f, 0.5f, 0.5f)
        prlColumn.activities = arrayOf(0.45f, 0.15f, 0.2f)
        ethaneStream.purchases = arrayOf(1f, 0f, 0f)
        lpgStream.purchases = arrayOf(1f, 1f, 0f)
        naphtaStream.purchases = arrayOf(1f, 0f, 1f)
        ethyleneStream.sails =  arrayOf(0.1f, 0.2f, 0.1f)
        ethyleneStream.minBoundsSales = arrayOf(0.05f,0.05f,0.05f)
        ethyleneStream.maxBoundsSales = arrayOf(1.00f,1.00f,1.00f)
        ethyleneStream.prices = arrayOf(60.0f,70.0f,70.0f)
        tspStream.sails =  arrayOf(0.45f, 0.15f, 0.2f)
        hdpeStream.sails =  arrayOf(2.0f, 0.5f, 0.5f)
        iaaStream.sails =  arrayOf(0.8325f, 0.2775f, 0.37f)
        waterStream.purchases =  arrayOf(0.3825f, 0.1275f, 0.17f)

        // Capacities calculating
        epUnit.calculateCaps()
        hdpeUnit.calculateCaps()
        iaaUnit.calculateCaps()

        for (i in units.indices) {
            units[i].streamBalance()
        }
        for (i in streams.indices) {
            streams[i].materialBalance(this)
        }

        //Setting current Stream and Unit
        currentUnit = epUnit
        currentStream = ethyleneStream
        measure = QuantityMeasure.PER_TIME_UNIT
    }
}