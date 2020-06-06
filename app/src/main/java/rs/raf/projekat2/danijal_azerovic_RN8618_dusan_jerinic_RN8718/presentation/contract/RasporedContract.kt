package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.RasporedState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.RasporedFilter

interface RasporedContract {

    interface ViewModel{

        val rasporedState: LiveData<RasporedState>

        fun fetchRaspored()
        fun getRaspored()
        fun getRasporedByFilter(filter: RasporedFilter)

    }

}