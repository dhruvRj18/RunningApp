package com.greydevelopers.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greydevelopers.runningapp.db.Run
import com.greydevelopers.runningapp.others.SortType
import com.greydevelopers.runningapp.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val repository: MainRepository
) : ViewModel(){

    private val runSortedbyDate = repository.getAllRunSortedByDate()
    private val runSortedbyDistance = repository.getAllRunSortedByDistance()
    private val runSortedbyCaloriesBurnt = repository.getAllRunSortedByCaloriesBurnt()
    private val runSortedbyTimeInMillis = repository.getAllRunSortedByTimeInMillis()
    private val runSortedbyAvgSpeed = repository.getAllRunSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()
    var sortType = SortType.DATE

    init {
        runs.addSource(runSortedbyDate){result->
            if (sortType == SortType.DATE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedbyDistance){result->
            if (sortType == SortType.DISTANCE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedbyCaloriesBurnt){result->
            if (sortType == SortType.CALORIES_BURNT){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedbyTimeInMillis){result->
            if (sortType == SortType.RUNNING_TIME){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedbyAvgSpeed){result->
            if (sortType == SortType.AVG_SPEED){
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType){
        SortType.DATE -> runSortedbyDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runSortedbyTimeInMillis.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runSortedbyAvgSpeed.value?.let { runs.value = it }
        SortType.DISTANCE -> runSortedbyDistance.value?.let { runs.value = it }
        SortType.CALORIES_BURNT -> runSortedbyCaloriesBurnt.value?.let { runs.value = it }
    }


    fun insertRun(run: Run) = viewModelScope.launch {
        repository.insertRun(run)
    }
}