package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
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
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities.EditNotesActivity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.adapter.BeleskeAdapter
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.BeleskeState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.BeleskeViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.BeleskeFilter
import timber.log.Timber

class BeleskeFragment: Fragment(R.layout.fragment_beleske) {

    private val beleskeViewModel: BeleskeContract.ViewModel by sharedViewModel<BeleskeViewModel>()
    private lateinit var beleskeAdapter: BeleskeAdapter

    companion object{
        const val MESSAGE_REQUEST_CODE_ADD = 1
        const val MESSAGE_REQUEST_CODE_EDIT = 2
        const val MESSAGE_KEY_TITLE = "title"
        const val MESSAGE_KEY_TEXT = "text"
        const val MESSAGE_KEY_BELESKA = "beleska"
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
                //Delete
                val beleskaEntity = BeleskaEntity(it.id.toInt(),it.title,it.text,it.archived)
                beleskeViewModel.deleteBeleska(beleskaEntity)
            },
            {
                //Edit
                val intent = Intent(context, EditNotesActivity::class.java)
                intent.putExtra(MESSAGE_KEY_BELESKA, it)
                startActivityForResult(intent, MESSAGE_REQUEST_CODE_EDIT)
            },
            {
                //Archive
                val archived: Int = if (it.archived) 0 else 1 // Reverse
                beleskeViewModel.updateBeleskaById(it.id.toInt(),it.title,it.text,archived)
                //Promenjeno da se datum ne bi osvezio svaki put...
            })
        recycler_beleske.adapter = beleskeAdapter
    }

    private fun initObservers() {
        beleskeViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        beleskeViewModel.getBeleskeByFilter(BeleskeFilter("",false))
    }

    private fun initListeners() {
        btn_add.setOnClickListener {
            val intent = Intent(context, AddNotesActivity::class.java)
            startActivityForResult(intent, MESSAGE_REQUEST_CODE_ADD)
        }
        //Swicth
        switch_arhivirane_beleske.setOnClickListener {
            val filter = pretraga_beleski.text.toString()
            if(switch_arhivirane_beleske.isChecked){
                beleskeViewModel.getBeleskeByFilter(BeleskeFilter(filter,true))
            }else{
                beleskeViewModel.getBeleskeByFilter(BeleskeFilter(filter,false))
            }
        }
        pretraga_beleski.doAfterTextChanged {
            val filter = it.toString()
            val archived = switch_arhivirane_beleske.isChecked
            beleskeViewModel.getBeleskeByFilter(BeleskeFilter(filter,archived))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == MESSAGE_REQUEST_CODE_ADD){
            data?.let {
                val title = data.getStringExtra(MESSAGE_KEY_TITLE)
                val text = data.getStringExtra(MESSAGE_KEY_TEXT)
                val beleskaEntity = BeleskaEntity(0,title,text)
                beleskeViewModel.insertBeleska(beleskaEntity)
            }
        }

        if(requestCode == MESSAGE_REQUEST_CODE_EDIT){
            data?.let {
                val beleska = data.getParcelableExtra<Beleska>(MESSAGE_KEY_BELESKA)
                val archived: Int = if (beleska.archived) 1 else 0
                beleskeViewModel.updateBeleskaById(beleska.id.toInt(),beleska.title,beleska.text,archived)
                //Promenjeno da se datum ne bi osvezio svaki put...
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