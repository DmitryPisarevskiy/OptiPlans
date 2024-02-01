package com.example.optiplans.entities

object ModelExample : Model(3) {
    init {
        this.periods[0] = 10
        this.periods[1] = 10
        this.periods[2] = 11
        val epUnit:Unit = Unit("ЭП-300", "MEP", 3)
        val s1: Stream = Stream("Этан", "ETN", 3)
        val s2: Stream = Stream("ШФЛУ", "SLU", 3)
        val s3: Stream = Stream("Нафта", "NAF", 3)
        val s4: Stream = Stream("Этилен", "ETY", 3)
        val s5: Stream = Stream("Пропилен", "PRP", 3)
        val s6: Stream = Stream("Пироконденсат", "PIR", 3)
        val c1:Column = Column("Переработка этана", "ETN", 3)
        val c2:Column = Column("Переработка ШФЛУ", "SLU", 3)
        val c3:Column = Column("Переработка нафты", "NAF", 3)
        c1.strAndCoeffs.put(s1, 1f)
        c1.strAndCoeffs.put(s4, -0.8f)
        c1.strAndCoeffs.put(s5, -0.1f)
        c1.strAndCoeffs.put(s6, -0.1f)
        c2.strAndCoeffs.put(s2, 1f)
        c2.strAndCoeffs.put(s4, -0.7f)
        c2.strAndCoeffs.put(s5, -0.15f)
        c2.strAndCoeffs.put(s6, -0.15f)
        c3.strAndCoeffs.put(s3, 1f)
        c3.strAndCoeffs.put(s4, -0.6f)
        c3.strAndCoeffs.put(s5, -0.2f)
        c3.strAndCoeffs.put(s6, -0.2f)
        epUnit.regimes.add(c1)
        epUnit.regimes.add(c2)
        epUnit.regimes.add(c3)
        this.units.add(epUnit)
        val hdpeUnit: Unit = Unit("ПЭНД", "PND", 3)
    }
}