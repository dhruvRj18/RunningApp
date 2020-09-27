package com.greydevelopers.runningapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timeStamp DESC")
    fun getAllRunSortedByDate():LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeInMillis DESC")
    fun getAllRunSortedByTimeInMillis():LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurnt DESC")
    fun getAllRunSortedByCaloriesBurnt():LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedinKMH DESC")
    fun getAllRunSortedByAvgSpeedInKMH():LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunSortedByDistance():LiveData<List<Run>>

    @Query("SELECT SUM(timeInMillis) FROM running_table")
    fun getTotalTimeInMillis():LiveData<Long>

    @Query("SELECT SUM(caloriesBurnt) FROM running_table")
    fun getTotalCaloriesBurnt():LiveData<Int>

    @Query("SELECT SUM(avgSpeedinKMH) FROM running_table")
    fun getTotalAvgSpeedInKMH():LiveData<Float>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance():LiveData<Int>


}