package com.example.desafiofirebase_sarah.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiofirebase_sarah.domain.Game
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.lang.Exception

class GameViewModel: ViewModel() {

    val games = MutableLiveData<ArrayList<Game>>()
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var cr: CollectionReference = db.collection("games")

    fun readGames() {

        var aux = arrayListOf<Game>()
        var game_name = ""
        var game_lancamento = ""
        var game_desc = ""
        var game_image = ""

        try {

            viewModelScope.launch {
                cr.get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            for (document in task.result!!) {

                                game_name = document.data["name"] as String
                                game_lancamento = document.data["create_at"] as String
                                game_desc = document.data["desc"] as String
                                game_image = document.data["img"] as String
                                aux.add(Game(game_name, game_lancamento, game_desc, game_image))
                            }

                            games.value = aux

                            Log.i("GameViewModel", games.value.toString())
                        } else {
                            Log.w("GameViewModel", "Error getting documents.", task.exception)
                        }
                    }
            }
        } catch (e: Exception) {

                Log.e("GameViewModel", e.toString())
        }
    }
}