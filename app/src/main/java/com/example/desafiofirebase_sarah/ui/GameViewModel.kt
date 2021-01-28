package com.example.desafiofirebase_sarah.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiofirebase_sarah.domain.Game
import java.lang.Exception

class GameViewModel: ViewModel() {

    val games = MutableLiveData<ArrayList<Game>>()

    fun readGames() {
        try {
             games.value = arrayListOf<Game>(
                 Game("Game 1", "2020", "abc", "img1"),
                 Game("Game 2", "2020", "abc", "img1"),
                 Game("Game 3", "2020", "abc", "img1"),
                 Game("Game 4", "2020", "abc", "img1"),
                 Game("Game 5", "2020", "abc", "img1")
             )
        } catch (e: Exception) {

            Log.e("GameViewModel", e.toString())
        }
    }
}