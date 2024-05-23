package model

import java.time.LocalDateTime

class Reservation(val start: LocalDateTime, val slut: LocalDateTime) {
    val pladser: MutableList<Plads> = mutableListOf()
}