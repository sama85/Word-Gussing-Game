/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */

const val GAME_FRAGMENT = "GameFragment"

class GameFragment : Fragment() {

    private lateinit var viewModel : GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        //provider class create one-time vm and associates it with UI controller
        //on recreation, provider returns a reference to the existing vm class
        Log.i(GAME_FRAGMENT, "called view model provider")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // observe data in view model through observer object
//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore->
//            binding.scoreText.text = newScore.toString()
//        })
//
//        viewModel.word.observe(viewLifecycleOwner, Observer{ newWord ->
//            binding.wordText.text = newWord
//        })

        viewModel.isGameFinished.observe(viewLifecycleOwner, Observer{ isGameFinished ->
            if(isGameFinished == true) {
                gameFinished()
                viewModel.gameFinishedComplete()
            }
        })

        //formatting live data should be handled in view model
//        viewModel.currentTime.observe(viewLifecycleOwner, Observer{ newTime ->
//            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
//        })

        //view model contains UI data, so bind it directly to views
        //to utilize data binding instead of onclick listeners
        binding.gameViewModel = viewModel

        //allows us to use live data in view model directly with views
        //instead of using observer code in UI controller
        binding.setLifecycleOwner(this)

        //process data from vm and update ui
//        binding.correctButton.setOnClickListener {
//            viewModel.onCorrect()
//        }
//        binding.skipButton.setOnClickListener {
//            viewModel.onSkip()
//        }

        return binding.root
    }
    /**
     * Called when the game is finished
     */
    //navigation needs navcontroller which exists in fragment (kept in UI Controller)
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value?: 0)
        findNavController(this).navigate(action)
    }

}
