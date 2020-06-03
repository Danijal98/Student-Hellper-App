package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.adapters.PagerAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initMainFragment()
    }

    private fun initMainFragment(){
        mainFragment.adapter = PagerAdapter(supportFragmentManager)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_1 -> {
                    mainFragment.setCurrentItem(PagerAdapter.FRAGMENT_1,false)
                }
                R.id.nav_2 -> {
                    mainFragment.setCurrentItem(PagerAdapter.FRAGMENT_2,false)
                }
                R.id.nav_3 -> {
                    mainFragment.setCurrentItem(PagerAdapter.FRAGMENT_3,false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
