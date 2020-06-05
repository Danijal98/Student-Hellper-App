package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Raspored

class RasporedDiffItemCallback : DiffUtil.ItemCallback<Raspored>(){

    override fun areItemsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.predmet == newItem.predmet
                && oldItem.tip == newItem.tip
                && oldItem.nastavnik == newItem.nastavnik
                && oldItem.grupe == newItem.grupe
                && oldItem.dan == newItem.dan
                && oldItem.termin == newItem.termin
                && oldItem.ucionica == newItem.ucionica
    }


}