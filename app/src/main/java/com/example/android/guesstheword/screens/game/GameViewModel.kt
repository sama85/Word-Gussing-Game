package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val GAME_VIEW_MODEL = "GameViewModel"

class GameViewModel : ViewModel() {

    // The current word
    val word = MutableLiveData<String>()
    // The current score
    val score = MutableLiveData<Int>()
    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{
        Log.i(GAME_VIEW_MODEL, "GameViewModel created")

        /**
         * Resets the list of words and randomizes the order once
         */
        score.value = 0
        word.value = ""
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
            word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score.value = score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = score.value?.plus(1)
        nextWord()
    }
    //view model is destroyed when associated activity/frag is completely
    // destroyed (not config. change /process shutdown that recreates the activity
    override fun onCleared() {
        super.onCleared()
        Log.i(GAME_VIEW_MODEL, "GameViewModel destroyed")
    }

}