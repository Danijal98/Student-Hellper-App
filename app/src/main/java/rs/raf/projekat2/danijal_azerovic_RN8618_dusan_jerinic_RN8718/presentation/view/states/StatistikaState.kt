package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states

sealed class StatistikaState {
    object Loading: StatistikaState()
    data class Success(val statistika: List<Int>): StatistikaState()
    data class Error(val message: String): StatistikaState()
}