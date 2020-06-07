package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_statistika.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.BeleskeContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.StatistikaState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.BeleskeViewModel
import timber.log.Timber

class StatistikaFragment: Fragment(R.layout.fragment_statistika) {

    private val beleskeViewModel: BeleskeContract.ViewModel by sharedViewModel<BeleskeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initObservers()
    }

    private fun initObservers() {
        beleskeViewModel.statistikaState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        beleskeViewModel.getBeleske()
    }

    private fun renderState(state: StatistikaState) {
        when(state){
            is StatistikaState.Success -> {
                updateGraph(state.statistika)
            }
            is StatistikaState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is StatistikaState.Loading -> {
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateGraph(list: List<Int>){
        val list2 = listOf<Int>(3,1,4,2,5)
        stats.heights = list
        stats.invalidate()
    }

}