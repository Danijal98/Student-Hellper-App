package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import kotlin.collections.ArrayList

class StatistikaFragment: Fragment(R.layout.fragment_statistika) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initGraph()
    }

    private fun initGraph(){

    }
}