package examskotlintingtang.model

import java.time.LocalDate
import java.time.LocalTime

class Booking(val dato: LocalDate,
              val tid: LocalTime,
              val single: Boolean,
              // link 0..* --- 1 Bane
              val bane: Bane,
                // Link 0..* --- 1 spiller, komposition

              val spiller: Spiller) {




}