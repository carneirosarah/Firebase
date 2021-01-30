package com.example.desafiofirebase_sarah.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafiofirebase_sarah.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), GameAdapter.OnClickGameListener {

    lateinit var adapter: GameAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    val viewModel : GameViewModel = GameViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        adapter = GameAdapter(this)
        rv_game.adapter = adapter
        gridLayoutManager = GridLayoutManager(this, 2)
        rv_game.layoutManager = gridLayoutManager
        rv_game.hasFixedSize()

        viewModel.games.observe(this) {
            adapter.addGames(it)
        }

        viewModel.readGames()

        button_add_game.setOnClickListener {
            startActivity(Intent(this, CadastroGameActivity::class.java))
        }
    }

    override fun onClickGame(position: Int) {

        val game = viewModel.games.value?.get(position)
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("game", game)
        startActivity(intent)
    }
}