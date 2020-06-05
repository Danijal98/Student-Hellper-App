package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_notes.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R

class EditNotesActivity : AppCompatActivity(R.layout.activity_edit_notes)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners(){
        button_sacuvaj.setOnClickListener {
            val naslov = novi_naslov.text
            val text = novi_text.text
            //TODO
        }
        button_odustani.setOnClickListener {
            //TODO
            finish()
        }
    }

}