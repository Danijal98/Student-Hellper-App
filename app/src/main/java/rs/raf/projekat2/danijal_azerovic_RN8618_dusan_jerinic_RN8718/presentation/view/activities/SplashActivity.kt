package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.UserContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.UserViewModel

class SplashActivity : AppCompatActivity() {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initObservers()
    }

    private fun initObservers(){
        userViewModel.logged.observe(this, Observer {
            intent = if (it){
                Intent(this, MainActivity::class.java)
            }else{
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        })
        userViewModel.isLogged()
    }

}
