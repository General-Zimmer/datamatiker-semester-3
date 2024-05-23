package model

class KonsolPlads(val Type: String, nr: Int, omraade: Omraade) : Plads(nr, omraade) {

    override fun pris(timer : Int) : Double {
        val finalPris = when (this.omraade) {
            Omraade.BØRNE -> this.standardTimePris * (0.80+0.10)
            Omraade.STANDARD -> this.standardTimePris * (1.00+0.25)
            Omraade.VIP -> this.standardTimePris * (1.25+0.25)
            Omraade.TURNERING -> this.standardTimePris * (1.10+0.25)
        }

        return finalPris * timer
    }

    override fun compareTo(other: Plads): Int {
        return if (omraade == other.omraade)
            if (other is KonsolPlads)
                this.nr - other.nr
            else
                Int.MIN_VALUE
        else
            getOmraadeNumber(omraade) - getOmraadeNumber(other.omraade)
    }
}