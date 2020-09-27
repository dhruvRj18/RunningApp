package com.greydevelopers.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.greydevelopers.runningapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SetUpFragment : Fragment(R.layout.fragment_setup) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstAppOpen ){
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.setUpFragment,true)
                .build()
            findNavController().navigate(
                R.id.action_setUpFragment_to_runFragment,
                savedInstanceState,
                navOption
            )
        }



        tvContinue.setOnClickListener {
            val success = writePersonalInfoToSharedPref()
            if (success){
                findNavController().navigate(R.id.action_setUpFragment_to_runFragment)
            }else
            {
                Snackbar.make(requireView(),"Please Enter All the fields",Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    private fun writePersonalInfoToSharedPref(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        } else {
            sharedPreferences.edit()
                .putString("name", name)
                .putFloat("weight", weight.toFloat())
                .putBoolean("FIRST_TIME_TOGGLE", false)
                .apply()
            val toolbarText = "Let's Go, $name!"
            requireActivity().tvToolbarTitle.text = toolbarText
            return true
        }
    }


}