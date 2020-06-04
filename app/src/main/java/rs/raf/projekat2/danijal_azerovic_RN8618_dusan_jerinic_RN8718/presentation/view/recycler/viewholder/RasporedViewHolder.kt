package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.raspored_item.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Raspored

class RasporedViewHolder (
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer{

    @SuppressLint("SetTextI18n")
    fun bind(raspored: Raspored){
        predmet_tip.text = raspored.predmet + " " + raspored.tip
        profesor.text = raspored.nastavnik
        ucionica.text = raspored.ucionica
        grupe.text = raspored.grupe.toString()
        dan.text = raspored.dan
        vreme.text = raspored.termin
    }

}