package com.example.desafiofirebase_sarah.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiofirebase_sarah.R
import com.example.desafiofirebase_sarah.domain.Game
import com.squareup.picasso.Picasso


class GameAdapter (val listener: OnClickGameListener): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var games = arrayListOf<Game>()

    interface OnClickGameListener {

        fun onClickGame(position: Int)
    }

    inner class GameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameGame: TextView = itemView.findViewById(R.id.movie_name)
        var lancamentoGame: TextView = itemView.findViewById(R.id.movie_lancamento)
        var imgGame: ImageView = itemView.findViewById(R.id.movie_img)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {

                listener.onClickGame(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_game,
            parent,
            false
        )

        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        var game: Game = games.get(position)
        holder.nameGame.text = game.name
        holder.lancamentoGame.text = game.anoLancamento
        Picasso.get().load(game.image).into(holder.imgGame)
        // imagem aqui
    }

    override fun getItemCount(): Int = games.size

    fun addGames(newGames: ArrayList<Game>) {

        games = newGames
        notifyDataSetChanged()
    }
}