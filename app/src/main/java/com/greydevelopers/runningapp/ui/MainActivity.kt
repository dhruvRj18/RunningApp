package com.greydevelopers.runningapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.greydevelopers.runningapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import com.greydevelopers.runningapp.others.Constants.ACTION_SHOW_TRACKING_FRAGMENT

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToTrackingFragment(intent)

        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener { /*no operation*/ }

        /*    make bottom nav hidden in specific fragments     */
        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id){
                    R.id.statisticsFragment,R.id.runFragment,R.id.settingsFragment->
                        bottomNavigationView.visibility = View.VISIBLE
                    else->
                        bottomNavigationView.visibility = View.GONE
                }
            }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragment(intent)
    }

    private fun navigateToTrackingFragment(intent: Intent?){
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
            navHostFragment.findNavController().navigate(R.id.action_global_to_trackingFragment)
        }
    }


}








