package com.example.desafiofirebase_sarah.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiofirebase_sarah.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        login_tv_create_account.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}