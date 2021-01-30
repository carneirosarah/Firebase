package com.example.desafiofirebase_sarah.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiofirebase_sarah.R
import com.example.desafiofirebase_sarah.domain.Game
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        game_return.setOnClickListener{
            finish()
        }

        var game = intent.extras?.get("game") as Game
        Picasso.get().load(game.image).into(game_image)
        movie_name.text = game.name
        movie_lancamento.text = "Lançamento: " + game.anoLancamento
        movie_desc.text = game.descricao

    }
}