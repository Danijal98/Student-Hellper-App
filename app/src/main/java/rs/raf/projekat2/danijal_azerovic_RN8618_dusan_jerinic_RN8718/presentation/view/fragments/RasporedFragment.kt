package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_raspored.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.RasporedContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.RasporedViewModel
import timber.log.Timber
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.adapter.RasporedAdapter
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.recycler.diff.RasporedDiffItemCallback
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.RasporedState

class RasporedFragment: Fragment(R.layout.fragment_raspored) {

    private lateinit var rasporedAdapter: RasporedAdapter

    private val rasporedViewModel: RasporedContract.ViewModel by sharedViewModel<RasporedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initDropdowns()
        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initDropdowns(){
        val adapter1 = ArrayAdapter(context, R.layout.spinner_item, arrayOf("102", "103", "104"))
        adapter1.setDropDownViewResource(R.layout.spinner_item)
        grupa_dropdown.adapter = adapter1

        val adapter2 = ArrayAdapter(context, R.layout.spinner_item, arrayOf("PON", "UTO", "SRE", "CET", "PET"))
        adapter2.setDropDownViewResource(R.layout.spinner_item)

        dan_dropdown.adapter = adapter2
    }

    private fun initListeners(){

    }

    private fun initObservers(){
        rasporedViewModel.rasporedState.observe(viewLifecycleOwner,Observer{
            Timber.e(it.toString())
            renderState(it)
        })
        rasporedViewModel.getRaspored()
        rasporedViewModel.fetchRaspored()
    }

    private fun initRecycler(){
        recycler_raspored.layoutManager = LinearLayoutManager(activity)
        rasporedAdapter = RasporedAdapter(RasporedDiffItemCallback())
        recycler_raspored.adapter = rasporedAdapter
    }

    private fun renderState(state: RasporedState){
        when(state){
            is RasporedState.Success -> {
                rasporedAdapter.submitList(state.raspored)
            }
            is RasporedState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RasporedState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is RasporedState.Loading -> {
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

