package com.greydevelopers.runningapp.repositories

import com.greydevelopers.runningapp.db.Run
import com.greydevelopers.runningapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(val runDao: RunDao) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)


    fun getAllRunSortedByDate() = runDao.getAllRunSortedByDate()
    fun getAllRunSortedByDistance() = runDao.getAllRunSortedByDistance()
    fun getAllRunSortedByTimeInMillis() = runDao.getAllRunSortedByTimeInMillis()
    fun getAllRunSortedByAvgSpeed() = runDao.getAllRunSortedByAvgSpeedInKMH()
    fun getAllRunSortedByCaloriesBurnt() = runDao.getAllRunSortedByCaloriesBurnt()

    fun getTotalAvgSpeedInKMH() = runDao.getTotalAvgSpeedInKMH()
    fun getTotalDistance() = runDao.getTotalDistance()
    fun getTotalCaloriesBurnt() = runDao.getTotalCaloriesBurnt()
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
}