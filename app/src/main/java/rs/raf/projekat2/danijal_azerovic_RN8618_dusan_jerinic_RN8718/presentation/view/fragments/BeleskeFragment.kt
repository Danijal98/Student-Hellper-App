package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
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
                Toast.makeText(context, "Delete note", Toast.LENGTH_SHORT).show()
            },
            {
                //TODO edit note
                Toast.makeText(context, "Edit note", Toast.LENGTH_SHORT).show()
            },
            {
                //TODO archive note
                Toast.makeText(context, "Archive note", Toast.LENGTH_SHORT).show()
            })
        recycler_beleske.adapter = beleskeAdapter
    }

    private fun initObservers() {
        beleskeViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        beleskeViewModel.getBeleske()
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
                val beleskaEntity = BeleskaEntity(0,title,text)
                beleskeViewModel.insertBeleska(beleskaEntity)
            }
        }

    }

    private fun renderState(state: BeleskeState){
        when(state){
            is BeleskeState.Success -> {
                beleskeAdapter.submitList(state.beleske)
            }
            is BeleskeState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is BeleskeState.Loading -> {
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }
        }
    }

}