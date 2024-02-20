package examskotlintingtang.model

import java.time.LocalDate
import java.time.LocalTime

class Bane(val nummer: Int,
           val inde: Boolean,
           val førsteTid: LocalTime,
           val sidsteTid: LocalTime,
           //Link 0..* --> Kategori
           val kategori: Kategori) {

    private val _bookings : MutableList<Booking> = mutableListOf()
    val booking: List<Booking>
        get() = _bookings

    fun addBooking(booking: Booking) = _bookings.add(booking)

    fun bookedeTimerPåDato(dato: LocalDate): Int {
        var AntalBookedeTimer = 0
        for (booking in _bookings) {
            if (booking.dato == dato) {
                AntalBookedeTimer++
            }
        }
        return AntalBookedeTimer
    }

    // S4
    fun antalBookingingerPrTime(): IntArray {
        val count = sidsteTid.hour - førsteTid.hour + 1
        val bookingsCounts = IntArray(count)
        for (hour in førsteTid.hour .. sidsteTid.hour) {
            for (booking in _bookings) {
                if (booking.tid.hour == hour) {
                    bookingsCounts[hour - førsteTid.hour]++
                }
            }
        }
        return bookingsCounts
    }
}