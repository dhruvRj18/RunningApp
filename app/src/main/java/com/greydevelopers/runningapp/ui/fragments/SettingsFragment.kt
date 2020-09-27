package com.greydevelopers.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.greydevelopers.runningapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.etName
import kotlinx.android.synthetic.main.fragment_settings.etWeight
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment:Fragment(R.layout.fragment_settings ) {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPref()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPreference()
            if (success){
                Snackbar.make(view,"Changes Saved!", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(view,"Please fill all the values", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    private fun loadFieldsFromSharedPref(){
        val name = sharedPreferences.getString("name","")
        val weight = sharedPreferences.getFloat("weight",56f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPreference():Boolean{
        val nameText = etName.text.toString()
        val weightText = etWeight.text.toString()
        if (nameText.isEmpty() || weightText.isEmpty()){
            return false
        }
        sharedPreferences.edit()
            .putString("name",nameText)
            .putFloat("weight",weightText.toFloat())
            .apply()
        val toolbarText = "Let's Go $nameText!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}