package com.example.android.guesstheword.screens.score

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// A view model factory creates a view model with parameters using create method
class ScoreViewModelFactory(private val finalScore : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScoreViewModel(finalScore) as T
    }

}