package examskotlintingtang.controller

import examskotlintingtang.model.Bane
import examskotlintingtang.model.Booking
import examskotlintingtang.model.Kategori
import examskotlintingtang.model.Spiller
import examskotlintingtang.storage.Storage
import java.time.LocalDate
import java.time.LocalTime

object Controller {

    private val Storage: Storage = Storage()

    fun createBooking(dato: LocalDate, tid: LocalTime, single: Boolean, bane: Bane, spiller: Spiller): Booking {
        val booking = Booking(dato, tid, single, bane, spiller)
        spiller.addBooking(booking)
        bane.addBooking(booking)
        return booking
    }

    fun createSpiller(navn: String, uddannelse: String): Spiller {
        val spiller = Spiller(navn, uddannelse)
        Storage.addSpiller(spiller)
        return spiller
    }

    fun createBane(nummer: Int, inde: Boolean, førsteTid: LocalTime, sidsteTid: LocalTime, kategori: Kategori): Bane {
        val bane = Bane(nummer, inde, førsteTid, sidsteTid, kategori)
        Storage.addBane(bane)
        return bane
    }

    fun createKategori(navn: String, prisKrSingle: Int, PrisKrDouble: Int): Kategori {
        val kategori = Kategori(navn, prisKrSingle, PrisKrDouble)
        Storage.addKategori(kategori)
        return kategori
    }

    fun initStorage() {
        with(Controller) {
            val luksus = createKategori("Luksus", 100, 200)
            val mellem = createKategori("Mellem", 50, 100)
            val begynder = createKategori("Begynder", 25, 50)

            createBane(1, true, LocalTime.parse("09:00"), LocalTime.parse("17:00"), luksus)
            val bane2 = createBane(2, true, LocalTime.parse("09:00"), LocalTime.parse("17:00"), luksus)
            val bane3 = createBane(3, true, LocalTime.parse("09:00"), LocalTime.parse("17:00"), mellem)
            createBane(4, false, LocalTime.parse("09:00"), LocalTime.parse("17:00"), mellem)
            val bane5 = createBane(5, false, LocalTime.parse("09:00"), LocalTime.parse("17:00"), begynder)
            createBane(6, false, LocalTime.parse("09:00"), LocalTime.parse("17:00"), begynder)

            val andreas = createSpiller("Andreas", "DMU")
            createSpiller("Petra", "DMU")
            val henrik = createSpiller("Henrik", "ITA")
            val ulla = createSpiller("Ulla", "ITA")

            createBooking(LocalDate.parse("2023-06-20"), LocalTime.parse("10:00"), true, bane3, andreas)
            createBooking(LocalDate.parse("2023-06-22"), LocalTime.parse("10:00"), false, bane2, andreas)
            createBooking(LocalDate.parse("2023-06-20"), LocalTime.parse("11:00"), false, bane3, henrik)
            createBooking(LocalDate.parse("2023-06-20"), LocalTime.parse("16:00"), false, bane3, ulla)
            createBooking(LocalDate.parse("2023-06-23"), LocalTime.parse("17:00"), true, bane5, ulla)
        }
    }
}