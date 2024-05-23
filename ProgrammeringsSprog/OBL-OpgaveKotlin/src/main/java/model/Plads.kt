package model

open class Plads(val nr : Int, val omraade: Omraade) : Comparable<Plads>{
    val reservationer: MutableList<Reservation> = mutableListOf()

    companion object var standardTimePris = 50

    open fun pris(timer : Int) : Double {
        val finalPris = when (this.omraade) {
            Omraade.BØRNE -> this.standardTimePris * 0.80
            Omraade.STANDARD -> this.standardTimePris * 1.00
            Omraade.VIP -> this.standardTimePris * 1.25
            Omraade.TURNERING -> this.standardTimePris * 1.10
        }

        return finalPris * timer
    }

    override fun compareTo(other: Plads): Int {
        return if (omraade == other.omraade)
            this.nr - other.nr
        else
            getOmraadeNumber(omraade) - getOmraadeNumber(other.omraade)
    }
    fun getOmraadeNumber(omraade: Omraade) : Int {
        return when (omraade) {
            Omraade.BØRNE -> 1
            Omraade.STANDARD -> 2
            Omraade.TURNERING -> 3
            Omraade.VIP -> 4
        }
    }
}