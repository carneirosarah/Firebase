package com.example.desafiofirebase_sarah.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiofirebase_sarah.R
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        game_return.setOnClickListener{
            finish()
        }
    }
}