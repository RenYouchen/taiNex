package com.kot.tainex.account

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kot.tainex.Startup
import com.kot.tainex.util.AccountDB
import com.kot.tainex.databinding.LoginPageBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : LoginPageBinding
    private lateinit var db : AccountDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        db = AccountDB(this)
        setContentView(binding.root)
        val a = Intent(this, Startup::class.java)
        startActivity(a)
        finish()

        binding.loginMail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Check text here
                if(binding.loginMail.text.toString().contains(" ")) {
                    binding.loginMail.error = "不可包含空格"
                } else if (!binding.loginMail.text.toString().matches(Regex("[0-9a-zA-z_]+@[0-9a-zA-Z_]+.[0-9a-zA-Z_]+"))) {
                    binding.loginMail.error = "Email格式錯誤"
                } else {
                    binding.loginMail.error = null
                }
                if(binding.loginMail.text.toString().isBlank()){
                    binding.loginMail.error = "email might empty or have space char in string"
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.loginPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.loginPass.text.toString().contains(" ")) {
                    binding.loginPass.error = "不可包含空格"
                } else if(binding.loginPass.text.length < 6) {
                    binding.loginPass.error = "密碼不可小於6碼"
                } else if((!binding.loginPass.text.matches(Regex("[0-9a-zA-z]+")) || binding.loginPass.text.toString().contains("_"))) {
                    binding.loginPass.error = "密碼僅可包含英文與數字"
                } else {
                    binding.loginPass.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        val signup = Intent(this, SignupActivity::class.java);
        val main = Intent(this, Startup::class.java)
        binding.loginButton.setOnClickListener{
            if (db.readUser(binding.loginMail.text.toString(),binding.loginPass.text.toString())) {
                Toast.makeText(this,"登入成功！",Toast.LENGTH_SHORT).show()
                startActivity(main)
                finish()
            } else {
                Toast.makeText(this,"帳號或密碼錯誤",Toast.LENGTH_SHORT).show()
            }

        }
        binding.loginToSignup.setOnClickListener {
            startActivity(signup)
            finish()
        }
    }
}