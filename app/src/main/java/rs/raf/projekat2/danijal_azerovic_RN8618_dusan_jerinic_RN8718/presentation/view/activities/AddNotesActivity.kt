package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_notes.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R

class AddNotesActivity : AppCompatActivity(R.layout.activity_add_notes)   {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners(){
        nova_beleska_button_sacuvaj.setOnClickListener {
            val naslov = nova_beleska_naslov.text
            val text = nova_beleska_text.text
            //TODO
        }
        nova_beleska_button_odustani.setOnClickListener {
            //TODO
            finish()
        }
    }
}