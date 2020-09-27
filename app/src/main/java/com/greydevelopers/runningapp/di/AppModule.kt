package com.greydevelopers.runningapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.greydevelopers.runningapp.db.RunningDatabase
import com.greydevelopers.runningapp.others.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(
        context,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME

    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db:RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences("sharedPref",MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) = sharedPreferences.getString("name","")?:""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) = sharedPreferences.getFloat("weight",56f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) = sharedPreferences.getBoolean("FIRST_TIME_TOGGLE",true)


}