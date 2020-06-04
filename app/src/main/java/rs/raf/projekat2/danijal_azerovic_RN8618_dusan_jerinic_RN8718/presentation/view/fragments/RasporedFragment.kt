package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_raspored.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.RasporedContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.RasporedViewModel
import timber.log.Timber

class RasporedFragment: Fragment(R.layout.fragment_raspored) {

    private val rasporedViewModel: RasporedContract.ViewModel by sharedViewModel<RasporedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initDropdowns()
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
        })
        rasporedViewModel.getRaspored()
        rasporedViewModel.fetchRaspored()
    }

}

