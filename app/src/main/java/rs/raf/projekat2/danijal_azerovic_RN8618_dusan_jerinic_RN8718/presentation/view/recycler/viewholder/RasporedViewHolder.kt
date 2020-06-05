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

        when (raspored.dan){
            "PON" -> dan.text = "Ponedeljak:"
            "UTO" -> dan.text = "Utorak:"
            "SRE" -> dan.text = "Sreda:"
            "ČET" -> dan.text = "Četvrtak:"
            "PET" -> dan.text = "Petak:"
        }

        var grupeEdited = ""
        raspored.grupe.forEachIndexed{index, s ->
            grupeEdited += s
            if (index + 1 != raspored.grupe.size){
                grupeEdited += ","
            }
        }

        tip.text = raspored.tip + ":"
        predmet.text = raspored.predmet
        profesor.text = raspored.nastavnik
        ucionica.text = raspored.ucionica
        grupe.text = grupeEdited
        vreme.text = raspored.termin
    }

}