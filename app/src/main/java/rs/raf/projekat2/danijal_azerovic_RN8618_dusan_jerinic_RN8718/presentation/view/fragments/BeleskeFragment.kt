package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.BeleskeContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.adapter.BeleskeAdapter
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.BeleskeState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.BeleskeViewModel

class BeleskeFragment: Fragment(R.layout.fragment_beleske) {

    private val beleskeViewModel: BeleskeContract.ViewModel by sharedViewModel<BeleskeViewModel>()
    private lateinit var beleskeAdapter: BeleskeAdapter

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
        TODO("Not yet implemented")
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun renderState(state: BeleskeState){
        TODO("Not yet implemented")
    }

}