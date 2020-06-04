package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models

data class Raspored (
    val id: String,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: MutableList<String>,
    val dan: String,
    val termin: String,
    val ucionica: String
)