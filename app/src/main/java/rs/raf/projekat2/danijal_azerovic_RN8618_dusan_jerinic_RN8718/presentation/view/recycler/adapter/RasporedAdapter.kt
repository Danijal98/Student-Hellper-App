package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Raspored
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff.RasporedDiffItemCallback
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.viewholder.RasporedViewHolder

class RasporedAdapter (rasporedDiffItemCallback: RasporedDiffItemCallback): ListAdapter<Raspored, RasporedViewHolder>(rasporedDiffItemCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RasporedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.raspored_item, parent, false)
        return RasporedViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: RasporedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}