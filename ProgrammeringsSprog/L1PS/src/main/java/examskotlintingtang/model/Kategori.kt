package examskotlintingtang.model

data class Kategori(val navn: String, val prisKrSingle: Int, val PrisKrDouble: Int) {
    override fun toString(): String {
        return super.toString()
    }
}