package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val GAME_VIEW_MODEL = "GameViewModel"

class GameViewModel : ViewModel() {

    // Encapuslate live data
    //mutable should be private and returned as Live data to avoid changing it anywhere outside viewModel
    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    //returns score as live data when accessed to only observe/read
    val score : LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{
        Log.i(GAME_VIEW_MODEL, "GameViewModel created")

        /**
         * Resets the list of words and randomizes the order once
         */
        _score.value = 0
        _word.value = ""
        resetList()
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        _score.value = score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }
    //view model is destroyed when associated activity/frag is completely
    // destroyed (not config. change /process shutdown that recreates the activity
    override fun onCleared() {
        super.onCleared()
        Log.i(GAME_VIEW_MODEL, "GameViewModel destroyed")
    }

}