package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.BeleskeContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities.AddNotesActivity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.adapter.BeleskeAdapter
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.BeleskeState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.BeleskeViewModel
import timber.log.Timber

class BeleskeFragment: Fragment(R.layout.fragment_beleske) {

    private val beleskeViewModel: BeleskeContract.ViewModel by sharedViewModel<BeleskeViewModel>()
    private lateinit var beleskeAdapter: BeleskeAdapter

    companion object{
        const val MESSAGE_REQUEST_CODE = 1
        const val MESSAGE_KEY_TITLE = "title"
        const val MESSAGE_KEY_TEXT = "text"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initRecycler(){
        recycler_beleske.layoutManager = LinearLayoutManager(activity)
        beleskeAdapter = BeleskeAdapter(BeleskaDiffItemCallback(),
            {
                //TODO delete note
            },
            {
                //TODO edit note
            },
            {
                //TODO archive note
            })
    }

    private fun initObservers() {
        //TODO
    }

    private fun initListeners() {
        btn_add.setOnClickListener {
            val intent = Intent(context, AddNotesActivity::class.java)
            startActivityForResult(intent, MESSAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == MESSAGE_REQUEST_CODE){
            data?.let {
                val title = data.getStringExtra(MESSAGE_KEY_TITLE)
                val text = data.getStringExtra(MESSAGE_KEY_TEXT)
                val beleska = Beleska("0", title, text)
                Timber.e("$beleska")
            }
        }

    }

    private fun renderState(state: BeleskeState){
        //TODO
    }

}