package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.beleske_item.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska

class BeleskeViewHolder(override val containerView: View,
                        deleteNoteAction: (Int) -> Unit,
                        editNoteAction: (Int) -> Unit,
                        archiveNoteAction: (Int) -> Unit): RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        delete_beleska.setOnClickListener{ deleteNoteAction(adapterPosition) }
        edit_beleska.setOnClickListener { editNoteAction(adapterPosition) }
        archive_beleska.setOnClickListener { archiveNoteAction(adapterPosition) }
    }

    fun bind(beleska: Beleska){
        naslov.text = beleska.title
        beleska_text.text = beleska.text
    }

}