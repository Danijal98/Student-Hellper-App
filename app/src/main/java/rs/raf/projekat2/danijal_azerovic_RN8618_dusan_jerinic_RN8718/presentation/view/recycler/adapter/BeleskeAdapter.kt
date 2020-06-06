package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.adapter

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.beleske_item.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.viewholder.BeleskeViewHolder

class BeleskeAdapter(beleskaDiffItemCallback: BeleskaDiffItemCallback,
                     private val deleteNoteAction: (Beleska) -> Unit,
                     private val editNoteAction: (Beleska) -> Unit,
                     private val archiveNoteAction: (Beleska) -> Unit): ListAdapter<Beleska, BeleskeViewHolder>(beleskaDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeleskeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.beleske_item, parent, false)
        return BeleskeViewHolder(containerView, {deleteNoteAction(getItem(it))}, {editNoteAction(getItem(it))}, {archiveNoteAction(getItem(it))})
    }

    override fun onBindViewHolder(holder: BeleskeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}