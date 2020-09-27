package com.greydevelopers.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.greydevelopers.runningapp.R
import com.greydevelopers.runningapp.others.TrackingUtility
import com.greydevelopers.runningapp.ui.viewmodels.MainViewModel
import com.greydevelopers.runningapp.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.item_run.*
import javax.inject.Inject
import kotlin.math.round

@AndroidEntryPoint
class StatisticsFragment:Fragment(R.layout.fragment_statistics ) {

    private val viewModel  :StatisticsViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun setUpBarChart(){

    }



    private fun subscribeToObservers(){
        viewModel.totalTimeRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormettedStopWatchTime(it)
                tvTotalTime.text = totalTimeRun
            }
        })
        viewModel.totalDistance.observe(viewLifecycleOwner, Observer {
            it?.let {
                val km = it/1000f
                val totalDistance = round(km*10f)/10f
                val totalDistanceString = "${totalDistance}km"
                tvTotalDistance.text = totalDistanceString
            }
        })
        viewModel.totalAvgSpeed.observe(viewLifecycleOwner, Observer {
            it?.let {
                val avgSpeed = round(it*10f)/10f
                val avgSeedString = "${avgSpeed}km/h"
                tvAverageSpeed.text = avgSeedString
            }
        })
        viewModel.totalCaloriesBurnt.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalCalories = "${it}kcal"
                tvTotalCalories.text = totalCalories
            }
        })

    }
}