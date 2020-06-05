package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska

class BeleskaDiffItemCallback : DiffUtil.ItemCallback<Beleska>() {

    override fun areItemsTheSame(oldItem: Beleska, newItem: Beleska): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beleska, newItem: Beleska): Boolean {
        return oldItem.title == newItem.title
                && oldItem.text == newItem.text
                && oldItem.archived == newItem.archived
    }

}