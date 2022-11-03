package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

const val GAME_VIEW_MODEL = "GameViewModel"

class GameViewModel : ViewModel() {

    init{
        Log.i(GAME_VIEW_MODEL, "GameViewModel created")
    }

    //view model is destroyed when associated activity/frag is completely
    // destroyed (not config. change /process shutdown that recreates the activity
    override fun onCleared() {
        super.onCleared()
        Log.i(GAME_VIEW_MODEL, "GameViewModel destroyed")
    }
}