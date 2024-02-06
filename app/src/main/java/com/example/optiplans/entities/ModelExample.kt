package com.example.optiplans.entities

object ModelExample : Model(3) {
    init {
        // Periods initialization
        this.periods[0] = 10
        this.periods[1] = 10
        this.periods[2] = 11

        // Stream construction
        val epUnit = Unit("ЭП-300", "MEP", 3)
        val hdpeUnit = Unit("ПЭНД", "PND", 3)
        val iaaUnit = Unit("ЛАК", "PND", 3)
        val ethaneStream = Stream("Этан", "ETN", 3, bought = true)
        val lpgStream = Stream("ШФЛУ", "SLU", 3, bought = true)
        val naphtaStream = Stream("Нафта", "NAF", 3, bought = true)
        val ethyleneStream = Stream("Этилен", "ETY", 3, sold = true)
        val propyleneStream = Stream("Пропилен", "PRP", 3)
        val tspStream = Stream("Пироконденсат", "PIR", 3, sold = true)
        val hdpeStream = Stream("ПЭНД", "PND", 3, sold = true)
        val iaaStream = Stream("ЛАК", "LAK", 3, sold = true)
        val waterStream = Stream("Синтез-газ", "SGA", 3, bought = true)

        // Prices, costs
        ethaneStream.costs = arrayOf(20f,20f,20f)
        lpgStream.costs = arrayOf(10f,10f,10f)
        naphtaStream.costs = arrayOf(20f,20f,20f)
        ethyleneStream.prices = arrayOf(30f,30f,30f)
        tspStream.prices = arrayOf(10f,10f,10f)
        hdpeStream.prices = arrayOf(40f,40f,40f)
        iaaStream.prices = arrayOf(10f,10f,10f)
        waterStream.costs = arrayOf(10f,10f,10f)

        // Stream construction
        val etnColumn:Column = Column("Переработка этана", "ETN", 3)
        val sluColumn:Column = Column("Переработка ШФЛУ", "SLU", 3)
        val nafColumn:Column = Column("Переработка нафты", "NAF", 3)
        val etyColumn:Column = Column("Переработка этилена", "ETY", 3)
        val prlColumn:Column = Column("Переработка пропилена", "PRL", 3)

        // Column coefficients
        etnColumn.strAndCoeffs.put(ethaneStream, 1f)
        etnColumn.strAndCoeffs.put(ethyleneStream, -0.8f)
        etnColumn.strAndCoeffs.put(propyleneStream, -0.1f)
        etnColumn.strAndCoeffs.put(tspStream, -0.1f)
        sluColumn.strAndCoeffs.put(lpgStream, 1f)
        sluColumn.strAndCoeffs.put(ethyleneStream, -0.7f)
        sluColumn.strAndCoeffs.put(propyleneStream, -0.15f)
        sluColumn.strAndCoeffs.put(tspStream, -0.15f)
        nafColumn.strAndCoeffs.put(naphtaStream, 1f)
        nafColumn.strAndCoeffs.put(ethyleneStream, -0.6f)
        nafColumn.strAndCoeffs.put(propyleneStream, -0.2f)
        nafColumn.strAndCoeffs.put(tspStream, -0.2f)
        etyColumn.strAndCoeffs.put(ethyleneStream, 1.0f)
        etyColumn.strAndCoeffs.put(hdpeStream, -1.0f)
        prlColumn.strAndCoeffs.put(propyleneStream, 0.54f)
        prlColumn.strAndCoeffs.put(waterStream, 0.46f)
        prlColumn.strAndCoeffs.put(iaaStream, -1.0f)

        // Adding columns to units
        epUnit.regimes.add(etnColumn)
        epUnit.regimes.add(sluColumn)
        epUnit.regimes.add(nafColumn)
        hdpeUnit.regimes.add(etyColumn)
        iaaUnit.regimes.add(prlColumn)

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
    }
}