package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.fragment_statistika.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalTime.now
import java.util.*
import kotlin.collections.ArrayList

class StatistikaFragment: Fragment(R.layout.fragment_statistika) {

    lateinit var chart: BarChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initGraph()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initGraph(){
        chart = bar_chart
        val dataBarEntries = ArrayList<BarEntry>()
        val values = floatArrayOf(5.0F, 2.0F, 3.0F, 6.0F, 4.0F)
        values.forEachIndexed{index, element ->
            dataBarEntries.add(BarEntry(element, index.toFloat()))
        }
        val dataSet = BarDataSet(dataBarEntries, "Data")
//        dataSet.color = R.color.secondaryColor  ....Aha oce, samo malo sutra, stavis na crvenu, stavi je na plavo...
        val data = BarData(dataSet)
        bar_chart.data = data
    }
}