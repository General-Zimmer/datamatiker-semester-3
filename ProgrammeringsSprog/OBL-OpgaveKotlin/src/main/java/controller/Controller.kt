package controller

import model.*
import storage.Storage
import java.time.LocalDateTime
import java.time.Period

object Controller {

    val storage : Storage = Storage

    fun initStorage() {
        with(storage) {
            addPlads(Plads(1, Omraade.TURNERING))
            addPlads(Plads(2, Omraade.TURNERING))
            addPlads(Plads(3, Omraade.STANDARD))
            addPlads(Plads(4, Omraade.STANDARD))
            addPlads(Plads(5, Omraade.BØRNE))
            addPlads(KonsolPlads("PlayStation 4", 1, Omraade.VIP))
            addPlads(KonsolPlads("Xbox One", 2, Omraade.VIP))
            addArrangement(Arrangement("Dota 2 tournament", true))
            addArrangement(Arrangement("CS GO tournament", false))
            addReserveration(Reservation(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
            addReserveration(Reservation(LocalDateTime.now(), LocalDateTime.now().plusDays(3)))
        }

        fun findPlads(Omraade: Omraade, nummer : Int) : Plads? {
            return storage.pladser[storage.pladser.binarySearch { it.compareTo(Plads(nummer, Omraade))
            }]
        }

        fun createArrangement(navn: String, offentlig: Boolean) {
            storage.addArrangement(Arrangement(navn, offentlig))
        }

        fun createPlads(type: String, nr: Int, omraade: Omraade) {
            storage.addPlads(KonsolPlads(type, nr, omraade))
        }

        fun createReservation(start: LocalDateTime, slut: LocalDateTime) {
            storage.addReserveration(Reservation(start, slut))
        }

        fun linkPladsAndReserveration(plads: Plads, reservation: Reservation) {
            plads.reservationer.add(reservation)
            reservation.pladser.add(plads)
        }

        fun linkReservationAndArrangement(arrangement: Arrangement, reservation: Reservation) {
            arrangement.reservation.add(reservation)
        }
    }
}