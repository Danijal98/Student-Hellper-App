package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states

import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Raspored

sealed class RasporedState {
    object Loading: RasporedState()
    object DataFetched: RasporedState()
    data class Success(val raspored: List<Raspored>): RasporedState()
    data class Error(val message: String): RasporedState()
}