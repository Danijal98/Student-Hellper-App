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
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.RasporedFilter

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
        val adapter1 = ArrayAdapter(context, R.layout.spinner_item, arrayOf("GRUPE", "101", "102", "103", "104", "105", "106", "107", "Dodatna nastava", "1d1", "1d2", "1s2", "1s4", "1s3", "1s1", "201", "202", "203", "204", "210", "211", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "313", "2s1", "2s2", "311", "312", "401", "402", "403", "410", "3s1", "3s2", "2d1", "2d2", "2d3", "2d4", "3d1", "4d1"))
        adapter1.setDropDownViewResource(R.layout.spinner_item)
        grupa_dropdown.adapter = adapter1

        val adapter2 = ArrayAdapter(context, R.layout.spinner_item, arrayOf("DANI", "PON", "UTO", "SRE", "ČET", "PET"))
        adapter2.setDropDownViewResource(R.layout.spinner_item)

        dan_dropdown.adapter = adapter2
    }

    private fun initListeners(){
        search_raspored.setOnClickListener {
            var grupa = grupa_dropdown.selectedItem.toString()
            var dan = dan_dropdown.selectedItem.toString()
            var filter = RasporedFilter(grupa, dan, editText.text.toString())
            if (filter.grupa == "GRUPE") filter.grupa = ""
            if (filter.dan == "DANI") filter.dan = ""
            rasporedViewModel.getRasporedByFilter(filter)
        }
    }

    private fun initObservers(){
        rasporedViewModel.rasporedState.observe(viewLifecycleOwner,Observer{
            Timber.e(it.toString())
            renderState(it)
        })
        rasporedViewModel.getRasporedByFilter(RasporedFilter())
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

