package com.example.android.guesstheword.screens.score

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// to pass parameters to viewmodel, it should be created by view model factory
class ScoreViewModel(finalScore : Int) : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    //logic/decision to be made when play again button pressed should be in view model
    //ui controller observes this flag and uses view model for making decision
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain :LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        Log.i("ScoreViewModel", "final score is  $finalScore")
        _score.value = finalScore
    }

    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete(){
        _eventPlayAgain.value = false
    }



}