package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states

import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska

sealed class BeleskeState {
    object Loading: BeleskeState()
    data class Success(val beleske: List<Beleska>): BeleskeState()
    data class Error(val message: String): BeleskeState()
}