package com.kot.tainex.account

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kot.tainex.util.AccountDB
import com.kot.tainex.databinding.SignupPageBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding : SignupPageBinding
    private lateinit var db : AccountDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var error = false
        db = AccountDB(this)
        binding = SignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginMail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Check text here
                error = true
                if(binding.loginMail.text.toString().contains(" ")) {
                    binding.loginMail.error = "不可包含空格"
                } else if (!binding.loginMail.text.toString().matches(Regex("[0-9a-zA-z_]+@[0-9a-zA-Z_]+.[0-9a-zA-Z_]+"))) {
                    binding.loginMail.error = "Email格式錯誤"
                }else if(binding.setPass.text.length >= 30) {
                    binding.setPass.error = "Email不可大於30字元"
                } else {
                    error = false
                    binding.loginMail.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.setPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                error = true
                if(binding.setPass.text.toString().contains(" ")) {
                    binding.setPass.error = "不可包含空格"
                } else if(binding.setPass.text.length < 6) {
                    binding.setPass.error = "密碼不可小於6碼"
                } else if((!binding.setPass.text.matches(Regex("[0-9a-zA-z]+")) || binding.setPass.text.toString().contains("_"))) {
                    binding.setPass.error = "密碼僅可包含英文與數字"
                } else {
                    binding.setPass.error = null
                    error = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.confirmPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                error = true
                if (!binding.confirmPass.text.toString().equals(binding.setPass.text.toString())) {
                    binding.confirmPass.error = "密碼不同"
                } else {
                    error = false
                    binding.confirmPass.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.signupButton.setOnClickListener {
            if (!error && (binding.confirmPass.text.isNotEmpty() && binding.setPass.text.isNotEmpty() && binding.loginMail.text.isNotEmpty())) {
                //NO ERROR
                if (!db.checkUser(binding.loginMail.text.toString())){
                    db.addUser(binding.loginMail.text.toString(),binding.setPass.text.toString())
                    Toast.makeText(this, "注冊成功。", Toast.LENGTH_SHORT).show()
                    val login = Intent(this, LoginActivity::class.java);
                    startActivity(login)
                    finish()
                } else {
                    binding.loginMail.error = "電子郵件已注冊過。"
                    Toast.makeText(this, "電子郵件已注冊過。", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "無法創建帳號，請確認電子郵件及密碼格式是否正確。", Toast.LENGTH_SHORT).show()
            }
        }

        val login = Intent(this, LoginActivity::class.java);
        binding.signupToLogin.setOnClickListener {
            startActivity(login)
            finish()
        }
    }
}