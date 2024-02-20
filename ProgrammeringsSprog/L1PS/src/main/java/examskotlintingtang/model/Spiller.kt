package examskotlintingtang.model

import java.time.LocalDate

class Spiller(val navn: String, val uddanelse: String) {

    // Link 1 ---- 0..* booking, komposition
    private val _Bookings: MutableList<Booking> = mutableListOf()
    val Bookings: List<Booking>
        get() = _Bookings

    fun addBooking(booking: Booking) = _Bookings.add(booking)


    fun removeBooking(booking: Booking) = _Bookings.remove(booking)


    // S3
    fun samledePris(kategori: Kategori): Int {
        var pris = 0
        for (booking in _Bookings) {
            with(kategori) {
                if (booking.bane.kategori == this) {
                    pris += if (booking.single) {
                        prisKrSingle
                    } else {
                        PrisKrDouble
                    }
                }
            }
        }
        return pris
    }

    fun bookedeTimerPåDag(dato: LocalDate): Int = Bookings.count { dato == it.dato }
}