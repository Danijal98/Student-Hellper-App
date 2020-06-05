package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_notes.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments.BeleskeFragment
import timber.log.Timber

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
            val title = nova_beleska_naslov.text.toString()
            val text = nova_beleska_text.text.toString()
            if(title.isBlank() || text.isBlank()){
                Toast.makeText(this, "One of the fields is empty!", Toast.LENGTH_SHORT).show()
                nova_beleska_naslov.requestFocus()
                return@setOnClickListener
            }
            val returnIntent = Intent()
            Timber.e("$title, $text")
            returnIntent.putExtra(BeleskeFragment.MESSAGE_KEY_TITLE, title.trim())
            returnIntent.putExtra(BeleskeFragment.MESSAGE_KEY_TEXT, text.trim())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        nova_beleska_button_odustani.setOnClickListener {
            finish()
        }
    }
}