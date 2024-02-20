package examskotlintingtang.storage

import examskotlintingtang.model.Bane
import examskotlintingtang.model.Kategori
import examskotlintingtang.model.Spiller

class Storage {
    private val _baner: MutableList<Bane> = mutableListOf()
    val baner: List<Bane>
        get() = _baner

    fun addBane(bane: Bane) = _baner.add(bane)

    private val _spillere = mutableListOf<Spiller>()

    val Spillere: List<Spiller>
        get() = _spillere

    fun addSpiller(spiller: Spiller) = _spillere.add(spiller)

    private val _kategorier = mutableListOf<Kategori>()

    val Kategorier: List<Kategori>
        get() = _kategorier

    fun addKategori(kategori: Kategori) = _kategorier.add(kategori)


}