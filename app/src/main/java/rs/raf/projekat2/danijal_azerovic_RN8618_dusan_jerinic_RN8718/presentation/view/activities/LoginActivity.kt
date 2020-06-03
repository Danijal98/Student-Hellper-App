package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.UserContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners() {
        loginBtn.setOnClickListener {
            val name = nameEditText.text.toString()
            val pin = "1111"
            if(name.isNotBlank() && pinEditText.text.toString() == pin){
                userViewModel.insertUser(name)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                nameEditText.requestFocus()
                Toast.makeText(this,"Name is empty or pin is invalid! Psst, it's 1111 ;)",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
