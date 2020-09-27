package com.greydevelopers.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.greydevelopers.runningapp.repositories.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
    val repository: MainRepository
) : ViewModel(){

    val totalTimeRun = repository.getTotalTimeInMillis()
    val totalDistance = repository.getTotalDistance()
    val totalCaloriesBurnt = repository.getTotalCaloriesBurnt()
    val totalAvgSpeed = repository.getTotalAvgSpeedInKMH()

    val runSortedByDate = repository.getAllRunSortedByDate()
    
}