package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.BeleskeState

interface BeleskeContract {

    interface  ViewModel{

        val beleskeState: LiveData<BeleskeState>

        fun getBeleske()
        fun insertBeleska(beleskaEntity: BeleskaEntity)
        fun updateBeleska(beleskaEntity: BeleskaEntity)
        fun deleteBeleska(beleskaEntity: BeleskaEntity)

    }

}