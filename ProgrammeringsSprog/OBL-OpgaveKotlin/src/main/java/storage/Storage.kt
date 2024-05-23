package storage

import model.Arrangement
import model.Plads
import model.Reservation

object Storage {

    private val _pladser: MutableList<Plads> = mutableListOf()
    private val _reservationer: MutableList<Reservation> = mutableListOf()
    private val _arrangementer: MutableList<Arrangement> = mutableListOf()

    val arrangementer : List<Arrangement> get() = _arrangementer
    val reservationer : List<Reservation> get() = _reservationer
    val pladser : List<Plads> get() = _pladser

    fun addArrangement(arrangement: Arrangement) {
        _arrangementer.add(arrangement)
    }
    fun addReserveration(reservation: Reservation) {
        _reservationer.add(reservation)
    }
    fun addPlads(plads: Plads) {
        _pladser.add(plads)
    }
}