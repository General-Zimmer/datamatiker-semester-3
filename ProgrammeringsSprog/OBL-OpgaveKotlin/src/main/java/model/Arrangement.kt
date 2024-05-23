package model

class Arrangement(val navn: String, val offentlig: Boolean) {
    val reservation : MutableList<Reservation> = mutableListOf()
    fun antalReserveredePladser() : Int {return reservation.sumOf { it.pladser.size }}

}