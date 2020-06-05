package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.android.synthetic.main.activity_edit_notes.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments.BeleskeFragment
import timber.log.Timber

class EditNotesActivity : AppCompatActivity(R.layout.activity_edit_notes)  {

    lateinit var beleska: Beleska

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beleska = intent.getParcelableExtra(BeleskeFragment.MESSAGE_KEY_BELESKA)
        init()
    }

    private fun init(){
        populateView()
        initListeners()
    }

    private fun populateView() {
        novi_naslov.setText(beleska.title)
        novi_text.setText(beleska.text)
    }

    private fun initListeners(){
        button_sacuvaj.setOnClickListener {
            val title = novi_naslov.text.toString()
            val text = novi_text.text.toString()
            if(title.isBlank() || text.isBlank()){
                Toast.makeText(this, "One of the fields is empty!", Toast.LENGTH_SHORT).show()
                novi_naslov.requestFocus()
                return@setOnClickListener
            }
            val returnIntent = Intent()
            beleska.title = title
            beleska.text = text
            returnIntent.putExtra(BeleskeFragment.MESSAGE_KEY_BELESKA, beleska)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        button_odustani.setOnClickListener {
            finish()
        }
    }

}